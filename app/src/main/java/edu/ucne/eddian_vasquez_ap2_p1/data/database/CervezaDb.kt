package edu.ucne.eddian_vasquez_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.eddian_vasquez_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.eddian_vasquez_ap2_p1.data.local.entitiespackage.CervezaEntity


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