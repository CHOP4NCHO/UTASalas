package com.chopancho.utasalas.domain

data class RoomInfo(
    val name: String,
    val isTaken: Boolean,
    val currentClassName: String?,
    val currentTeacherName: String?,
    val scheduleEntries: List<ScheduleEntry> = emptyList()
)

data class ScheduleEntry(
    val time: String,
    val className: String,
    val dayIndex: Int = 0
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