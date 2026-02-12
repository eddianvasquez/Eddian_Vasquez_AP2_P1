package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_list

sealed interface CervezaListUiEvent {
    data class OnFilterChange(val filter: String) : CervezaListUiEvent
    data class Delete(val id: Int) : CervezaListUiEvent
}