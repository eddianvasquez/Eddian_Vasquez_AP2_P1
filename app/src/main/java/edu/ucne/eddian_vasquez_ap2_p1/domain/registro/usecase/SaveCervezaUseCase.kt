package edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza

import edu.ucne.Eddian_Vasquez_Ap2_P1.domain.Cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class SaveCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza) {
        if (cerveza.nombre.isBlank()) throw IllegalArgumentException("El nombre es obligatorio")
        if (cerveza.marca.isBlank()) throw IllegalArgumentException("La marca es obligatoria")
        if (cerveza.puntuacion !in 1..5) throw IllegalArgumentException("La puntuación debe ser entre 1 y 5")

        repository.save(cerveza)
    }
}