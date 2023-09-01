package org.demre.certificacion.model.remote

data class DetalleHeroes(

    val id: String,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val Año_creacion: Int,
    val color: String,
    val traduccion: Boolean,
)