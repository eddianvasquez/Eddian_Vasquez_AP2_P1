package edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.Cerveza

import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class DeleteCervezaUseCase @Inject constructor(private val repo: CervezaRepository) {
    suspend operator fun invoke(id: Int) = repo.delete(id)
}