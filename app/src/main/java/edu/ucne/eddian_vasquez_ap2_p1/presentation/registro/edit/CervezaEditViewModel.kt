package edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.cerveza_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.model.Cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.GetCervezaUseCase
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza.SaveCervezaUseCase
import edu.ucne.Eddian_Vasquez_Ap2_p1.presentation.navigation.Screen

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
                val cerveza = getCerveza(args.cervezaId)
                cerveza?.let {
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
            is CervezaEditEvent.NombreChanged -> {
                state = state.copy(nombre = event.v, errorMessage = null)
            }
            is CervezaEditEvent.MarcaChanged -> {
                state = state.copy(marca = event.v, errorMessage = null)
            }
            is CervezaEditEvent.PuntuacionChanged -> {
                state = state.copy(puntuacion = event.v, errorMessage = null)
            }
            CervezaEditEvent.Save -> {
                save()
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            try {

                val puntuacionInt = state.puntuacion.toIntOrNull() ?: 0

                val cerveza = Cerveza(
                    idCerveza = state.id,
                    nombre = state.nombre,
                    marca = state.marca,
                    puntuacion = puntuacionInt
                )


                saveCerveza(cerveza)


                state = state.copy(saved = true)

            } catch (e: Exception) {

                state = state.copy(errorMessage = e.message ?: "Error desconocido")
            }
        }
    }
}