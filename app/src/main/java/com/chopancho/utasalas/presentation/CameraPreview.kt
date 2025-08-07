package com.chopancho.utasalas.presentation

import android.content.Context
import android.graphics.Color
import android.util.Size
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.chopancho.utasalas.data.TextRecognitionAnalyzer
import com.chopancho.utasalas.domain.FakeRoomRepository
import com.chopancho.utasalas.domain.RoomInfo
import com.chopancho.utasalas.ui.theme.Typography
import java.util.Locale

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


    var detectedText: String by rememberSaveable { mutableStateOf("No text detected yet..") }
    val currentRoomInfo: RoomInfo? by viewmodel.currentRoomInfo.collectAsState()

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showSchedule by rememberSaveable { mutableStateOf(false) }

    fun onTextUpdated(updatedText: String) {
        detectedText = updatedText
        viewmodel.getFirstRoomName(detectedText)
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Bienvenido a UTASalas") }) },
    ) { paddingValues: PaddingValues ->
        ResponsiveLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Box( // Contenedor para enmarcar y controlar el tamaño exacto
                modifier = Modifier
                    .size(310.dp)
                    .border(2.dp, MaterialTheme.colorScheme.inversePrimary)
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        PreviewView(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(Color.BLACK)
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            scaleType = PreviewView.ScaleType.FIT_CENTER
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
            }
            Column (
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val infoAvailable = currentRoomInfo != null
                Text(
                    text = if (infoAvailable)  "Sala actual ${currentRoomInfo?.name?.capitalized()}" else "Escanea el nombre de una sala",
                    fontSize = Typography.titleLarge.fontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top=30.dp)

                )
                AnimatedVisibility (infoAvailable) {

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Ver información actual",
                            fontSize = Typography.bodyMedium.fontSize,
                        )
                    }
                }
                AnimatedVisibility(infoAvailable) {
                    Button(
                        onClick = { showSchedule = true },
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Ver horario",
                            fontSize = Typography.bodyMedium.fontSize,
                        )
                    }
                }
            }

        }

    }

    if (showDialog) {
        RoomInfoDialog(
            roomInfo = currentRoomInfo,
            onDismiss = { showDialog = false }
        )
    }
    if (showSchedule) {
        RoomScheduleDialog(
            roomInfo = currentRoomInfo,
            onDismiss = { showSchedule = false}
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
                    InfoRow(label = "Nombre del Aula", value = roomInfo.name.capitalized())
                    InfoRow(label = "¿Ocupada?", value = if (roomInfo.isTaken) "Sí" else "No")
                    InfoRow(label = "Clase actual", value = roomInfo.currentClassName ?: "Ninguna")
                    InfoRow(label = "Profesor actual", value = roomInfo.currentTeacherName ?: "Ninguno")
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
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun RoomScheduleDialog(
    roomInfo: RoomInfo?,
    onDismiss: () -> Unit
) {
    if (roomInfo != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar")
                }
            },
            title = {
                Text(text = "Horario de ${roomInfo.name.capitalized()}")
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(40.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(40.dp))
                        .heightIn(max = 410.dp) // evita que crezca demasiado
                ) {
                    items(roomInfo.scheduleEntries) { entry ->
                        ScheduleEntryItem(
                            horaLabel = entry.horaLabel,
                            asignaturaLabel = entry.asignaturaLabel
                        )
                    }
                }
            }
        )
    }

}
@Composable
fun ScheduleEntryItem(
    horaLabel: String,
    asignaturaLabel: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = horaLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = asignaturaLabel,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}




private fun startTextRecognition(
    context: Context,
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    onDetectedTextUpdated: (String) -> Unit
) {

    cameraController.imageAnalysisTargetSize = CameraController.OutputSize(Size(310, 310))
    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        TextRecognitionAnalyzer(onDetectedTextUpdated = onDetectedTextUpdated)
    )

    cameraController.bindToLifecycle(lifecycleOwner)
    previewView.controller = cameraController
}
private fun String.capitalized(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
        Locale.ROOT
    ) else it.toString() }
}
