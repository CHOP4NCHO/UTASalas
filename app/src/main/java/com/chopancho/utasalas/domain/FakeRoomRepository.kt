package com.chopancho.utasalas.domain

class FakeRoomRepository: RoomRepository {
    override fun getRoomInfo(name: String): RoomInfo {

        return when (name) {
            "guallatire" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Algoritmos y estructuras de Datos GRUPO A",
                currentTeacherName = "Ibar Ramírez",
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                )
            )

            "socompa" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null,
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                )
            )

            "licancabur" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Arquitectura de Software",
                currentTeacherName = "Andrés Colque",
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                )
            )

            "azufre" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Ayudantía de POO",
                currentTeacherName = "Pablo Varas",
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                )
            )

            "parinacota" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Sistemas Operativos",
                currentTeacherName = "Hector Ossandon",
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                )
            )

            "pomerape" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null,
                scheduleEntries = listOf(
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial"),
                    ScheduleEntry("08:00 AM", "Física Cuántica"),
                    ScheduleEntry("09:00 AM", "Álgebra Lineal"),
                    ScheduleEntry("10:00 AM", "Programación Android"),
                    ScheduleEntry("11:00 AM", "Inteligencia Artificial")
                )
            )

            else -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Error, no hay datos de esa sala",
                currentTeacherName = null
            )
        }
    }

    override fun getRoomNames(): List<String> {
        return listOf(
            "parinacota",
            "pomerape",
            "socompa",
            "azufre",
            "guallatire",
            "licancabur"
        )
    }
}