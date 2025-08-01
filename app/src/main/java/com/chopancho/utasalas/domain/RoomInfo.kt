package com.chopancho.utasalas.domain

data class RoomInfo(
    val name: String,
    val isTaken: Boolean,
    val currentClassName: String?,
    val currentTeacherName: String?
)



val validNames = listOf(
    "licancabur",
    "guallatire",
    "azufre",
    "socompa",
    "pomerape",
    "parinacota",
    "Licancabur",
    "Guallatire",
    "Azufre",
    "Socompa",
    "Pomerape",
    "Parinacota",
)