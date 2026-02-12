package edu.ucne.Eddian_Vasquez_Ap2_P1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.dao.CervezaDao
import edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.entitiespackage.CervezaEntity


@Database(
    entities = [
        CervezaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CervezaDb : RoomDatabase() {
    abstract fun cervezaDao(): CervezaDao
}