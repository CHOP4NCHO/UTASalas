package com.chopancho.utasalas.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import com.chopancho.utasalas.domain.RoomInfo
import com.chopancho.utasalas.domain.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReadTextViewmodel(
    private val roomRepository: RoomRepository
): ViewModel() {
    private val _currentText = MutableStateFlow("")
    val currentText = _currentText.asStateFlow()
    private val _currentRoomName = MutableStateFlow("")
    val currentRoomName = _currentRoomName.asStateFlow()

    private val _currentRoomInfo = MutableStateFlow<RoomInfo?>(null)
    val currentRoomInfo = _currentRoomInfo.asStateFlow()




    fun getFirstRoomName(rawTextString: String) {
        val availableRoomNames = roomRepository.getRoomNames()
        Log.i("SALAS", availableRoomNames.toString())
        availableRoomNames.forEach {
            if (rawTextString.lowercase().contains(it, false)) {
                Log.i("SALAS", "HOLA, SI HAY ALGO: $it")
                _currentRoomName.value = it.lowercase()
                _currentRoomInfo.value = roomRepository.getRoomInfo(it.lowercase())
            }
        }
    }
}