package org.demre.certificacion.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lista_heroe")
data class ListaHeroesEntity(

    @PrimaryKey val id: String,
    val nombre: String,
    val origen: String,
    val imagenLink: String,
    val poder: String,
    val creacion: Int
)