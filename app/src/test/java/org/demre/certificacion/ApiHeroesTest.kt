package org.demre.certificacion

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.demre.certificacion.model.remote.ApiHeroes
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.After
import retrofit2.create
import org.demre.certificacion.model.remote.ListaHeroes

class ApiHeroesTest {

    private var mockWebServer: MockWebServer? = null
    private lateinit var api: ApiHeroes

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer!!.url("/"))
            .build()

        api = retrofit.create()
    }

    @After
    fun tearDown() {
        mockWebServer?.shutdown()
    }

    @Test
    fun obtenerListaTelefonos() = runBlocking {
        val listaResultados = listOf(
            ListaHeroes(id = "1", nombre = "Batman", origen = "Sin origen", imagenLink = "imagen1.jpg", poder = "UNO", Año_creacion = 44),
            ListaHeroes(id = "2", nombre = "Superman", origen = "Krypton", imagenLink = "imagen2.jpg", poder = "DOS", Año_creacion = 33)
        )
        mockWebServer!!.enqueue(MockResponse().setBody(Gson().toJson(listaResultados)))

        val result = api.getListaHeroes()
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()
        val body = result.body()
        assertThat(body).isNotNull()
        assertThat(body!!.size).isEqualTo(2)
        assertThat(body[0].nombre).isEqualTo("Batman")
        assertThat(body[1].Año_creacion).isEqualTo(33)
    }
}
