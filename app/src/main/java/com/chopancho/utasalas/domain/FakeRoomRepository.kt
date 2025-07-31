package com.chopancho.utasalas.domain

class FakeRoomRepository: RoomRepository {
    override fun getRoomInfo(name: String): RoomInfo {

        return when (name) {
            "guallatire" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Algoritmos y estructuras de Datos GRUPO A",
                currentTeacherName = "Ibar Ramírez"
            )

            "socompa" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null
            )

            "licancabur" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Arquitectura de Software",
                currentTeacherName = "Andrés Colque"
            )

            "azufre" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Ayudantía de POO",
                currentTeacherName = "Pablo Varas"
            )

            "parinacota" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Sistemas Operativos",
                currentTeacherName = "Hector Ossandon"
            )

            "pomerape" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null
            )

            else -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Error, no hay datos de esa sala",
                currentTeacherName = null
            )
        }
    }
}