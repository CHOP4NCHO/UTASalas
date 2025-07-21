package com.chopancho.utasalas.domain

import android.graphics.Bitmap

interface TextReader {
    fun read(bitmap: Bitmap, rotation: Int): List<ReadText>
}