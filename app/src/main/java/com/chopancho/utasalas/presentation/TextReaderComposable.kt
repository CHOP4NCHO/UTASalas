package com.chopancho.utasalas.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.chopancho.utasalas.domain.ReadText
import com.chopancho.utasalas.domain.TextReader

class TextReaderComposable(
    private val reader: TextReader,
    private val onResults: (List<ReadText>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(100, 32)

            val results = reader.read(bitmap,rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}