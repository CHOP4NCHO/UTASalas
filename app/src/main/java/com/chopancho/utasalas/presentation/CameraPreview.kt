package com.chopancho.utasalas.presentation

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.chopancho.utasalas.data.TextRecognitionAnalyzer
import com.chopancho.utasalas.domain.FakeRoomRepository
import com.chopancho.utasalas.domain.RoomInfo

@Composable
fun CameraScreen() {
    val viewmodel = ReadTextViewmodel(FakeRoomRepository())
    CameraContent(viewmodel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CameraContent(
    viewmodel: ReadTextViewmodel
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }


    var detectedText: String by remember { mutableStateOf("No text detected yet..") }
    val currentRoomInfo: RoomInfo? by viewmodel.currentRoomInfo.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    fun onTextUpdated(updatedText: String) {
        detectedText = updatedText
        viewmodel.getFirstRoomName(detectedText)
    }


    LaunchedEffect(currentRoomInfo) {
        if (currentRoomInfo != null) {
            showDialog = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Text Scanner") }) },
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setBackgroundColor(Color.BLACK)
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { previewView ->
                        startTextRecognition(
                            context = context,
                            cameraController = cameraController,
                            lifecycleOwner = lifecycleOwner,
                            previewView = previewView,
                            onDetectedTextUpdated = ::onTextUpdated
                        )
                    }
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.White)
                    .padding(16.dp),
                text = detectedText,
            )
        }
    }
    if (showDialog) {
        RoomInfoDialog(
            roomInfo = currentRoomInfo,
            onDismiss = { showDialog = false }
        )
    }
}


@Composable
fun RoomInfoDialog(
    roomInfo: RoomInfo?,
    onDismiss: () -> Unit
) {
    if (roomInfo != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Información del Aula", style = MaterialTheme.typography.titleLarge)
            },
            text = {
                Column {
                    InfoRow(label = "Nombre del Aula", value = roomInfo.name)
                    InfoRow(label = "¿Ocupada?", value = if (roomInfo.isTaken) "Sí" else "No")
                    InfoRow(
                        label = "Clase actual",
                        value = roomInfo.currentClassName ?: "Ninguna"
                    )
                    InfoRow(
                        label = "Profesor actual",
                        value = roomInfo.currentTeacherName ?: "Ninguno"
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


private fun startTextRecognition(
    context: Context,
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    onDetectedTextUpdated: (String) -> Unit
) {

    cameraController.imageAnalysisTargetSize = CameraController.OutputSize(AspectRatio.RATIO_16_9)
    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        TextRecognitionAnalyzer(onDetectedTextUpdated = onDetectedTextUpdated)
    )

    cameraController.bindToLifecycle(lifecycleOwner)
    previewView.controller = cameraController
}
