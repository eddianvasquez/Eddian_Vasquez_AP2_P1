package edu.ucne.Eddian_Vasquez_Ap2_P1.domain.usecase.cerveza

import edu.ucne.Eddian_Vasquez_Ap2_P1.domain.Cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.repository.CervezaRepository


import javax.inject.Inject

class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza): Result<Unit> {
        return runCatching {

            require(cerveza.nombre.isNotBlank()) { "El nombre es obligatorio " }
            require(cerveza.marca.isNotBlank()) { "La marca es obligatoria 🏷" }
            require(cerveza.puntuacion in 1..5) { "La puntuación debe ser entre 1 y 5 " }


            repository.save(cerveza)
        }
    }
}