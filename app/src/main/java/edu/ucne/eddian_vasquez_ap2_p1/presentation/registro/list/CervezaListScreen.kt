package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CervezaListScreen(
    viewModel: CervezaListViewModel = hiltViewModel(),
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val filter by viewModel.filter.collectAsState()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("BeerTracker 🍺") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Icon(Icons.Default.Add, null) }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {

            OutlinedTextField(
                value = filter,
                onValueChange = { viewModel.onEvent(CervezaListUiEvent.OnFilterChange(it)) },
                label = { Text("Filtrar por Nombre o Marca") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))


            LazyColumn(modifier = Modifier.weight(1f)) {
                items(state.cervezas) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onEdit(item.idCerveza!!) },
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(item.nombre, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Text(item.marca, style = MaterialTheme.typography.bodyMedium)
                            }

                            Text("⭐ ${item.puntuacion}", style = MaterialTheme.typography.titleLarge, color = Color(0xFFD4AF37))
                        }
                    }
                }
            }


            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total: ${state.totalCervezas}", fontWeight = FontWeight.Bold)
                    Text(
                        "Promedio: ${DecimalFormat("#.0").format(state.promedioPuntuacion)}",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}