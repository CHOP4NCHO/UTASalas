package com.chopancho.utasalas.domain

interface RoomRepository {

    fun getRoomInfo(name: String): RoomInfo
}