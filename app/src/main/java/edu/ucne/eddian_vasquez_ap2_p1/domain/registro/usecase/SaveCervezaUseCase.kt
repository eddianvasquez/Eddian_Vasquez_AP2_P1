package edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza

import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.model.Cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class SaveCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza) {
        if (cerveza.nombre.isBlank()) throw IllegalArgumentException("El nombre es obligatorio")
        repository.save(cerveza)
    }
}