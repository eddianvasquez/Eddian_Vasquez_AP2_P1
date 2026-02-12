package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_list

import edu.ucne.Eddian_Vasquez_Ap2_P1.domain.Cerveza


data class CervezaListUiState(
    val cervezas: List<Cerveza> = emptyList(),
    val totalCervezas: Int = 0,
    val promedioPuntuacion: Double = 0.0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)