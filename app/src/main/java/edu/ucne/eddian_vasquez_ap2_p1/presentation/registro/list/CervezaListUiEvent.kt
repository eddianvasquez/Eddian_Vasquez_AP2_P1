package edu.ucne.eddian_vasquez_ap2_p1.presentation.cerveza_list

sealed interface CervezaListUiEvent {
    data class OnFilterChange(val filter: String) : CervezaListUiEvent
    data class Delete(val id: Int) : CervezaListUiEvent
}