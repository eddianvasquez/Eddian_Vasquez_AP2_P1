package edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.mapper

import edu.ucne.Eddian_Vasquez_Ap2_P1.data.local.entitiespackage.CervezaEntity
import edu.ucne.Eddian_Vasquez_Ap2_P1.domain.Cerveza


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