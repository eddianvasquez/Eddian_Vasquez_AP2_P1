package edu.ucne.Eddian_Vasquez_Ap2_p1.domain.usecase.cerveza
import edu.ucne.Eddian_Vasquez_Ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class ObserveCervezasUseCase @Inject constructor(private val repo: CervezaRepository) {
    operator fun invoke() = repo.getAll()
}