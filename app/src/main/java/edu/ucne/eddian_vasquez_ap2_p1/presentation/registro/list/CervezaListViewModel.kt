package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_list.CervezaListUiState
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.DeleteCervezaUseCase
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.ObserveCervezasUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CervezaListViewModel @Inject constructor(
    private val observeCervezas: ObserveCervezasUseCase,
    private val deleteCerveza: DeleteCervezaUseCase
) : ViewModel() {

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    val uiState = combine(observeCervezas(), _filter) { lista, filtro ->

        val filtrada = if (filtro.isBlank()) lista else lista.filter {
            it.nombre.contains(filtro, true) || it.marca.contains(filtro, true)
        }


        val promedio = if (filtrada.isNotEmpty()) {
            filtrada.map { it.puntuacion }.average()
        } else 0.0

        CervezaListUiState(
            cervezas = filtrada,
            totalCervezas = filtrada.size,
            promedioPuntuacion = promedio
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CervezaListUiState())

    fun onEvent(event: CervezaListUiEvent) {
        when(event) {
            is CervezaListUiEvent.OnFilterChange -> _filter.value = event.filter
            is CervezaListUiEvent.Delete -> viewModelScope.launch { deleteCerveza(event.id) }
        }
    }
}