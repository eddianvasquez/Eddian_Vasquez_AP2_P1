package edu.ucne.eddian_vasquez_ap2_p1.data.local.entitiespackage
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cervezas")
data class CervezaEntity(
    @PrimaryKey(autoGenerate = true)
    val idCerveza: Int? = null,
    val nombre: String,
    val marca: String,
    val puntuacion: Int
)