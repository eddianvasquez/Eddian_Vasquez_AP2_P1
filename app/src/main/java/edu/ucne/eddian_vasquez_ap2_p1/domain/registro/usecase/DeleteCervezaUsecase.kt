package edu.ucne.eddian_vasquez_ap2_p1.domain.registro.usecase

import edu.ucne.eddian_vasquez_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class DeleteCervezaUseCase @Inject constructor(private val repo: CervezaRepository) {
    suspend operator fun invoke(id: Int) = repo.delete(id)
}