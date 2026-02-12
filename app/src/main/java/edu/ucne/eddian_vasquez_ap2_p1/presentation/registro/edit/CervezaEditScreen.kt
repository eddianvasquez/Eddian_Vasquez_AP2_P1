package edu.ucne.eddian_vasquez_ap2_p1.presentation.registro.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_edit.CervezaEditEvent
import edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_edit.CervezaEditState

@Composable
fun CervezaEditScreen(
    viewModel: CervezaEditViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val state = viewModel.state

    LaunchedEffect(state.saved) {
        if (state.saved) goBack()
    }


    CervezaEditBody(
        state = state,
        onEvent = viewModel::onEvent,
        goBack = goBack
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaEditBody(
    state: CervezaEditState,
    onEvent: (CervezaEditEvent) -> Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (state.id == null) "Nueva Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Default.ArrowBack, "Atras")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(CervezaEditEvent.Save) }) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (state.errorMessage != null) {
                Text(
                    text = state.errorMessage ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            OutlinedTextField(
                value = state.nombre,
                onValueChange = { onEvent(CervezaEditEvent.NombreChanged(it)) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorMessage != null && state.nombre.isBlank()
            )

            OutlinedTextField(
                value = state.marca,
                onValueChange = { onEvent(CervezaEditEvent.MarcaChanged(it)) },
                label = { Text("Marca") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorMessage != null && state.marca.isBlank()
            )

            OutlinedTextField(
                value = state.puntuacion,
                onValueChange = { onEvent(CervezaEditEvent.PuntuacionChanged(it)) },
                label = { Text("Puntuacion (1-5)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorMessage != null && (state.puntuacion.toIntOrNull() ?: 0) !in 1..5
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CervezaEditScreenPreview() {

    val estadoFalso = CervezaEditState(
        nombre = "Presidente",
        marca = "Cerveceria",
        puntuacion = "5"
    )

    CervezaEditBody(
        state = estadoFalso,
        onEvent = {},
        goBack = {}
    )
}