package edu.ucne.eddian_vasquez_ap2_p1.data.repository

import edu.ucne.eddian_vasquez_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.eddian_vasquez_ap2_p1.data.local.entitiespackage.CervezaEntity
import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza
import edu.ucne.eddian_vasquez_ap2_p1.domain.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class CervezaRepositoryImpl @Inject constructor(
    private val dao: CervezaDao
) : CervezaRepository {
    override suspend fun save(cerveza: Cerveza) {
        dao.save(cerveza.toEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override suspend fun getCerveza(id: Int): Cerveza? {
        return dao.find(id)?.toDomain()
    }

    override fun getAll(): Flow<List<Cerveza>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }
}

fun CervezaEntity.toDomain() = Cerveza(idCerveza, nombre, marca, puntuacion)
fun Cerveza.toEntity() = CervezaEntity(idCerveza, nombre, marca, puntuacion)