package org.demre.certificacion.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalle_heroe")
data class DetalleHeroesEntity(

    @PrimaryKey val id: String,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val creacion: Int,
    val color: String,
    val traduccion: Boolean,
)