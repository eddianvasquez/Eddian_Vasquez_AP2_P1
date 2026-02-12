package edu.ucne.eddian_vasquez_ap2_p1.domain.registro.usecase

import edu.ucne.eddian_vasquez_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class ObserveCervezasUseCase @Inject constructor(private val repo: CervezaRepository) {
    operator fun invoke() = repo.getAll()
}