package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_edit

data class CervezaEditState(
    val id: Int? = null,
    val nombre: String = "",
    val marca: String = "",
    val puntuacion: String = "",
    val errorMessage: String? = null,
    val saved: Boolean = false
)