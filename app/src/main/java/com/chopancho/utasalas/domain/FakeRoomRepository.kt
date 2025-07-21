package com.chopancho.utasalas.domain

class FakeRoomRepository: RoomRepository {
    override fun getRoomInfo(name: String): RoomInfo {

        return when (name) {
            "Guallatire" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Algoritmos y estructuras de Datos GRUPO A",
                currentTeacherName = "Ibar Ramírez"
            )

            "Socompa" -> RoomInfo(
                name = name, isTaken = false,
                currentClassName = null,
                currentTeacherName = null
            )

            "Licancabur" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Arquitectura de Software",
                currentTeacherName = "Andrés Colque"
            )

            "Azufre" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Ayudantía de POO",
                currentTeacherName = "Pablo Varas"
            )

            "Parinacota" -> RoomInfo(
                name = name, isTaken = true,
                currentClassName = "Sistemas Operativos",
                currentTeacherName = "Hector Ossandon"
            )

            "Pomerape" -> RoomInfo(
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