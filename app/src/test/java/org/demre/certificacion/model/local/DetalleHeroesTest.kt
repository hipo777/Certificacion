package org.demre.certificacion.model.local

import org.junit.Test
import org.junit.Assert.assertEquals

data class DetalleHeroesEntity(
    val id: String,
    val nombre: String,
    val origen: String,
    val imagen: String,
    val poder: String,
    val creacion: Int,
    val color: String,
    val traduccion: Boolean
)

class DetalleHeroesTest {

    @Test
    fun toDetalleHeroesEntity() {
        val detalleheroes = DetalleHeroesEntity(
            id = "1",
            nombre = "Batman",
            origen = "Sin origen",
            imagen = "imagen1.jpg",
            poder = "Sin poder",
            creacion = 33,
            color = "Negro",
            traduccion = true
        )

        assertEquals(detalleheroes.id, detalleheroes.id)
        assertEquals(detalleheroes.nombre, detalleheroes.nombre)
        assertEquals(detalleheroes.origen, detalleheroes.origen)
        assertEquals(detalleheroes.imagen, detalleheroes.imagen)
        assertEquals(detalleheroes.poder, detalleheroes.poder)
        assertEquals(detalleheroes.creacion, detalleheroes.creacion)
        assertEquals(detalleheroes.color, detalleheroes.color)
        assertEquals(detalleheroes.traduccion, detalleheroes.traduccion)
    }
}
