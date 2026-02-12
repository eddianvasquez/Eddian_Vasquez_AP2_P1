package edu.ucne.Eddian_Vasquez_Ap2_P1.presentation.cerveza_edit

sealed interface CervezaEditEvent {
    data class NombreChanged(val v: String) : CervezaEditEvent
    data class MarcaChanged(val v: String) : CervezaEditEvent
    data class PuntuacionChanged(val v: String) : CervezaEditEvent
    data object Save : CervezaEditEvent
}