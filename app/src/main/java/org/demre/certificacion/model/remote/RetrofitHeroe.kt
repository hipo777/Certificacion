package org.demre.certificacion.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHeroe {
    companion object {

        private const val BASE_URL = "https://y-mariocanedo.vercel.app/"

        fun instanciaApiHeroes(): ApiHeroes {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)  // Configuración de la URL base
                .addConverterFactory(GsonConverterFactory.create())  // Utilización de Gson para la conversión de datos
                .build()

            return retrofit.create(ApiHeroes::class.java)
        }
    }
}