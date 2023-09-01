package org.demre.certificacion.model

import android.util.Log
import org.demre.certificacion.model.local.DaoHeroe
import org.demre.certificacion.model.local.DetalleHeroesEntity
import org.demre.certificacion.model.remote.RetrofitHeroe

class Repository(private val daoHeroe: DaoHeroe) {

    private val servicioDeRed = RetrofitHeroe.instanciaApiHeroes()
    val listaHeroesLiveData = daoHeroe.getListaHeroes()

    suspend fun getListaHeroesRed() {

        val apiRedLista = kotlin.runCatching { servicioDeRed.getListaHeroes() }

        apiRedLista.onSuccess {
            when (it.code()) {

                in 200..299 -> it.body()?.let { listaHeroes ->

                    daoHeroe.insertListaHeroes(
                        listaHeroesParaListaHeroesEntity(
                            listaHeroes
                        )
                    )
                }

                else -> Log.d("Repositorio", "${it.code()} - ${it.errorBody()}")
            }
        }

        apiRedLista.onFailure {

            Log.e("Error", "${it.message}")
        }
    }

    suspend fun getDetalleHeroesRed(id: String): DetalleHeroesEntity? {

        val apiRedDetalle = kotlin.runCatching { servicioDeRed.getDetalleHeroe(id) }


        return apiRedDetalle.getOrNull()?.body()?.let { detalleHeroes ->

            val heroeDetalleEntity = detalleHeroesParaDetalleHeroesEntity(detalleHeroes)
            daoHeroe.insertDetalleHeroes(heroeDetalleEntity)

            heroeDetalleEntity
        }
    }
}
