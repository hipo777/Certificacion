package org.demre.certificacion.model

import org.demre.certificacion.model.local.DetalleHeroesEntity
import org.demre.certificacion.model.local.ListaHeroesEntity
import org.demre.certificacion.model.remote.DetalleHeroes
import org.demre.certificacion.model.remote.ListaHeroes

fun listaHeroesParaListaHeroesEntity(heroesLista: List<ListaHeroes>): List<ListaHeroesEntity> {

    return heroesLista.map { itListaHeroes ->
        ListaHeroesEntity(
            id = itListaHeroes.id,
            nombre = itListaHeroes.nombre,
            origen = itListaHeroes.origen,
            imagenLink = itListaHeroes.imagenLink,
            poder = itListaHeroes.poder,
            creacion = itListaHeroes.Año_creacion
        )
    }
}

fun detalleHeroesParaDetalleHeroesEntity(itDetalleHeroes: DetalleHeroes): DetalleHeroesEntity {

    return DetalleHeroesEntity(
        id = itDetalleHeroes.id,
        nombre = itDetalleHeroes.nombre,
        origen = itDetalleHeroes.origen,
        imagenLink = itDetalleHeroes.imagenLink,
        poder = itDetalleHeroes.poder,
        creacion = itDetalleHeroes.Año_creacion,
        color = itDetalleHeroes.color,
        traduccion = itDetalleHeroes.traduccion
    )
}
