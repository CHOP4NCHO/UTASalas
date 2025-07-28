package com.chopancho.utasalas.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.util.Log
import com.chopancho.utasalas.domain.ReadText
import com.chopancho.utasalas.domain.TextReader
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import androidx.core.graphics.createBitmap
import java.nio.ByteBuffer
import java.nio.ByteOrder

class TFLiteTextReader(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResults: Int = 3
): TextReader {

    // Modelo y tamaños esperados (ajusta a tu modelo)
    private val modelPath = "model.tflite"
    private val inputWidth = 100
    private val inputHeight = 32

    private val interpreter: Interpreter by lazy {
        Interpreter(loadModelFile())
    }

    private val imageProcessor: ImageProcessor by lazy {
        ImageProcessor.Builder()
            .add(ResizeOp(inputHeight, inputWidth, ResizeOp.ResizeMethod.BILINEAR)) // nota: height,width
            .add(NormalizeOp(127.5f, 127.5f)) // Normaliza a [-1,1]
            .build()
    }

    override fun read(bitmap: Bitmap, rotation: Int): List<ReadText> {
        // Paso 1: rotar imagen si es necesario
        val rotatedBitmap = if (rotation != 0) {
            val matrix = Matrix().apply {
                postRotate(rotation.toFloat())
            }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } else {
            bitmap
        }

        // Paso 2: obtener forma y tipo de dato del modelo
        val inputTensor = interpreter.getInputTensor(0)
        val inputShape = inputTensor.shape()       // [1, 1, 32, 100]
        val inputDataType = inputTensor.dataType() // FLOAT32

        Log.d("TFLite", "Input tensor shape: ${inputShape.contentToString()}")
        Log.d("TFLite", "Input tensor data type: $inputDataType")

        // Paso 3: preparar imagen (asegurando tamaño 100x32 y escala de grises porque canal=1)
        val resizedBitmap = Bitmap.createScaledBitmap(rotatedBitmap, inputShape[3], inputShape[2], true)
        val bitmapForModel = toGrayscale(resizedBitmap) // Convierte a escala de grises

        // Paso 4: cargar bitmap en TensorImage (NHWC)
        val tensorImage = TensorImage(inputDataType)
        tensorImage.load(bitmapForModel)

        // Paso 5: procesar imagen (normalización si FLOAT32)
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(inputShape[2], inputShape[3], ResizeOp.ResizeMethod.BILINEAR)) // height, width
            .add(
                if (inputDataType == DataType.FLOAT32)
                    NormalizeOp(127.5f, 127.5f) // Normaliza a [-1,1]
                else
                    NormalizeOp(0f, 1f)
            )
            .build()

        val processedImage = imageProcessor.process(tensorImage)
        val nhwcBuffer = processedImage.buffer

        // Paso 6: Convertir buffer NHWC [1,32,100,1] a NCHW [1,1,32,100]
        val nchwBuffer = ByteBuffer.allocateDirect(1 * 1 * 32 * 100 * 4).order(ByteOrder.nativeOrder())
        val floatBuffer = nhwcBuffer.asFloatBuffer()

        for (h in 0 until 32) {
            for (w in 0 until 100) {
                val nhwcIndex = h * 100 + w
                val value = floatBuffer.get(nhwcIndex)
                nchwBuffer.putFloat(value)
            }
        }
        nchwBuffer.rewind()

        // Paso 7: preparar buffer de salida
        val outputTensor = interpreter.getOutputTensor(0)
        val outputShape = outputTensor.shape() // Ej: [1, 32, 62]
        val outputBuffer = Array(outputShape[0]) {
            Array(outputShape[1]) {
                FloatArray(outputShape[2])
            }
        }

        // Paso 8: correr la inferencia con el buffer NCHW
        interpreter.run(nchwBuffer, outputBuffer)

        // Paso 9: decodificar resultados
        val textResult = decodeOutput(outputBuffer)
        return listOf(ReadText(textResult))
    }



    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    // Decodificador placeholder: convierte la salida del modelo a texto
    private fun decodeOutput(output: Array<Array<FloatArray>>): String {
        val charMap = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val sb = StringBuilder()

        for (timestep in output[0]) {
            val maxIndex = timestep.indices.maxByOrNull { timestep[it] } ?: 0
            if (maxIndex < charMap.length) {
                sb.append(charMap[maxIndex])
            }
        }
        return sb.toString()
    }

    fun toGrayscale(source: Bitmap): Bitmap {
        // Crea bitmap vacío con mismo tamaño que la fuente
        val grayscale = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(grayscale)
        val paint = Paint().apply {
            colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
        }
        canvas.drawBitmap(source, 0f, 0f, paint)
        return grayscale
    }
}

