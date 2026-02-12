package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.Eddian_Vasquez_Ap2_P1.presentation.cerveza_edit.CervezaEditEvent
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.GetCervezaUseCase
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.SaveCervezaUseCase
import edu.ucne.Eddian_Vasquez_Ap2_P1.domain.Cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.navigation.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CervezaEditViewModel @Inject constructor(
    private val saveCerveza: SaveCervezaUseCase,
    private val getCerveza: GetCervezaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CervezaEditState())
        private set


    private val args = savedStateHandle.toRoute<Screen.CervezaEdit>()

    init {
        if (args.cervezaId != 0) {
            viewModelScope.launch {
                getCerveza(args.cervezaId)?.let {
                    state = state.copy(
                        id = it.idCerveza,
                        nombre = it.nombre,
                        marca = it.marca,
                        puntuacion = it.puntuacion.toString()
                    )
                }
            }
        }
    }

    fun onEvent(event: CervezaEditEvent) {
        when(event) {
            is CervezaEditEvent.NombreChanged -> state = state.copy(nombre = event.v, errorMessage = null)
            is CervezaEditEvent.MarcaChanged -> state = state.copy(marca = event.v, errorMessage = null)
            is CervezaEditEvent.PuntuacionChanged -> state = state.copy(puntuacion = event.v, errorMessage = null)
            CervezaEditEvent.Save -> save()
        }
    }

    private fun save() {
        viewModelScope.launch {
            try {
                val puntuacionInt = state.puntuacion.toIntOrNull() ?: 0
                saveCerveza(
                    Cerveza(
                        idCerveza = state.id,
                        nombre = state.nombre,
                        marca = state.marca,
                        puntuacion = puntuacionInt
                    )
                )
                state = state.copy(saved = true)
            } catch (e: Exception) {
                state = state.copy(errorMessage = e.message)
            }
        }
    }
}