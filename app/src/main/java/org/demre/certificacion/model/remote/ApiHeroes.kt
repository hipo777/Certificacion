package org.demre.certificacion.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHeroes {

    @GET("superheroes")
    suspend fun getListaHeroes(): Response<List<ListaHeroes>>

    @GET("superheroes/{id}")
    suspend fun getDetalleHeroe(@Path("id") id: String): Response<DetalleHeroes>
}