package edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.entitiespackage.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {
    @Upsert
    suspend fun save(cerveza: CervezaEntity)

    @Query("SELECT * FROM cervezas WHERE idCerveza = :id")
    suspend fun find(id: Int): CervezaEntity?

    @Query("DELETE FROM cervezas WHERE idCerveza = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM cervezas ORDER BY idCerveza DESC")
    fun getAll(): Flow<List<CervezaEntity>>
}