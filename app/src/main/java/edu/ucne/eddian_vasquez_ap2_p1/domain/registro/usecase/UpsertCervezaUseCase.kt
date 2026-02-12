package edu.ucne.eddian_vasquez_ap2_p1.domain.registro.usecase

import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza
import edu.ucne.eddian_vasquez_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza): Result<Unit> {
        return runCatching {

            if (cerveza.nombre.isBlank()) {
                throw IllegalArgumentException("El nombre es obligatorio")
            }


            if (cerveza.marca.isBlank()) {
                throw IllegalArgumentException("La marca es obligatoria")
            }


            if (cerveza.puntuacion < 1 || cerveza.puntuacion > 5) {
                throw IllegalArgumentException("La puntuacion debe ser entre 1 y 5")
            }


            repository.save(cerveza)
        }
    }
}