package edu.ucne.eddian_vasquez_ap2_p1.data.local.mapper

import edu.ucne.eddian_vasquez_ap2_p1.data.local.entitiespackage.CervezaEntity
import edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model.Cerveza


fun CervezaEntity.toDomain() = Cerveza(
    idCerveza = idCerveza,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)

fun Cerveza.toEntity() = CervezaEntity(
    idCerveza = idCerveza,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)