package edu.ucne.eddian_vasquez_ap2_p1.presentation.registro.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.DecimalFormat
import edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_list.CervezaListUiEvent
import edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_list.CervezaListViewModel
import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza
import edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_list.CervezaListUiState

@Composable
fun CervezaListScreen(
    viewModel: CervezaListViewModel = hiltViewModel(),
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val filter by viewModel.filter.collectAsState()

    CervezaListBody(
        state = state,
        filter = filter,
        onEvent = viewModel::onEvent,
        onAdd = onAdd,
        onEdit = onEdit
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListBody(
    state: CervezaListUiState,
    filter: String,
    onEvent: (CervezaListUiEvent) -> Unit,
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Cervezas", fontWeight = FontWeight.Bold) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.FilterList, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = filter,
                        onValueChange = { onEvent(CervezaListUiEvent.OnFilterChange(it)) },
                        placeholder = { Text("Filtrar por nombre...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }


            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.cervezas) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEdit(item.idCerveza!!) },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = item.marca,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }


                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "${item.puntuacion}/5",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.width(8.dp))


                                IconButton(
                                    onClick = { onEvent(CervezaListUiEvent.Delete(item.idCerveza!!)) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }


            Surface(
                tonalElevation = 8.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cant: ${state.totalCervezas}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Prom: ${DecimalFormat("#.0").format(state.promedioPuntuacion)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CervezaListScreenPreview() {
    val listaFalsa = listOf(
        Cerveza(1, "Presidente", "Dominicana", 5),
        Cerveza(2, "Corona", "Mexico", 4),
        Cerveza(3, "Heineken", "Holanda", 3)
    )

    val estadoFalso = CervezaListUiState(
        cervezas = listaFalsa,
        totalCervezas = 3,
        promedioPuntuacion = 4.0
    )

    CervezaListBody(
        state = estadoFalso,
        filter = "",
        onEvent = {},
        onAdd = {},
        onEdit = {}
    )
}