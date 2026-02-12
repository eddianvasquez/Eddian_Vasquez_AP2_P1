package edu.ucne.eddian_vasquez_ap2_p1.domain.registro.model

data class Cerveza(
    val idCerveza: Int? = null,
    val nombre: String,
    val marca: String,
    val puntuacion: Int
)