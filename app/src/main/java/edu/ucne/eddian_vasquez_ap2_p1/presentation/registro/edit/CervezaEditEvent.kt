package edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_edit

sealed interface CervezaEditEvent {
    data class NombreChanged(val v: String) : CervezaEditEvent
    data class MarcaChanged(val v: String) : CervezaEditEvent
    data class PuntuacionChanged(val v: String) : CervezaEditEvent
    data object Save : CervezaEditEvent
}