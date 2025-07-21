package com.chopancho.utasalas.data

import android.content.Context
import android.graphics.Bitmap
import com.chopancho.utasalas.domain.ReadText
import com.chopancho.utasalas.domain.TextReader
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class TFLiteTextReader(
    private val context: Context,
    private val threshold: Float = 0.5f,
    private val maxResults: Int = 3
): TextReader {

    // Modelo y tamaños esperados (ajusta a tu modelo)
    private val modelPath = "model.tflite"
    private val inputWidth = 310
    private val inputHeight = 310

    private val interpreter: Interpreter by lazy {
        Interpreter(loadModelFile())
    }

    private val imageProcessor: ImageProcessor by lazy {
        ImageProcessor.Builder()
            .add(ResizeOp(inputHeight, inputWidth, ResizeOp.ResizeMethod.BILINEAR)) // nota: height,width porque es ResizeOp(height,width)
            .add(NormalizeOp(127.5f, 127.5f)) // Normaliza a [-1,1]
            .build()
    }

    override fun read(bitmap: Bitmap, rotation: Int): List<ReadText> {
        // Paso 1: rotar imagen si es necesario
        val rotatedBitmap = if (rotation != 0) {
            val matrix = android.graphics.Matrix().apply {
                postRotate(rotation.toFloat())
            }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } else {
            bitmap
        }

        // Paso 2: convertir a TensorImage y procesar (resize + normalize)
        val tensorImage = TensorImage.fromBitmap(rotatedBitmap)
        val processedImage = imageProcessor.process(tensorImage)

        // Paso 3: preparar buffer para entrada y salida
        val inputBuffer = processedImage.buffer

        // Asumiendo que el modelo tiene una sola entrada y salida
        val outputShape = interpreter.getOutputTensor(0).shape()  // Ejemplo: [1, seq_length, num_classes]
        val outputDataType = interpreter.getOutputTensor(0).dataType()

        // Aquí asumimos salida float, ajusta si tu modelo es diferente
        val outputBuffer = Array(outputShape[0]) {
            Array(outputShape[1]) {
                FloatArray(outputShape[2])
            }
        }

        // Paso 4: correr la inferencia
        interpreter.run(inputBuffer, outputBuffer)

        // Paso 5: decodificar la salida para obtener texto
        val textResult = decodeOutput(outputBuffer)

        // Devuelve los resultados (ajusta según el tipo ReadText)
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
}
