package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.Eddian_Vasquez_Ap2_P1.presentation.cerveza_edit.CervezaEditEvent
import edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_edit.CervezaEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaEditScreen(
    viewModel: CervezaEditViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val state = viewModel.state

    LaunchedEffect(state.saved) {
        if (state.saved) goBack()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (state.id == null) "Nueva Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Default.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = state.nombre,
                onValueChange = { viewModel.onEvent(CervezaEditEvent.NombreChanged(it)) },
                label = { Text("Nombre (Ej: Presidente)") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = state.marca,
                onValueChange = { viewModel.onEvent(CervezaEditEvent.MarcaChanged(it)) },
                label = { Text("Marca/Origen") },
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                value = state.puntuacion,
                onValueChange = { viewModel.onEvent(CervezaEditEvent.PuntuacionChanged(it)) },
                label = { Text("Puntuación (1-5)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = { viewModel.onEvent(CervezaEditEvent.Save) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}