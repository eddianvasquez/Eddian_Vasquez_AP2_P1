package edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_list

import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza


data class CervezaListUiState(
    val cervezas: List<Cerveza> = emptyList(),
    val totalCervezas: Int = 0,
    val promedioPuntuacion: Double = 0.0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)