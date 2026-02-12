package edu.ucne.eddian_vasquez_ap2_p1.domain.repository


import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    suspend fun save(cerveza: Cerveza)
    suspend fun delete(id: Int)
    suspend fun getCerveza(id: Int): Cerveza?
    fun getAll(): Flow<List<Cerveza>>
}