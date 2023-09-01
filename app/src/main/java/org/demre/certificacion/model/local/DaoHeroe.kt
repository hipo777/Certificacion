package org.demre.certificacion.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoHeroe {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListaHeroes(listaComic: List<ListaHeroesEntity>)

    @Query("SELECT * FROM lista_heroe")
    fun getListaHeroes(): LiveData<List<ListaHeroesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalleHeroes(detalleComic: DetalleHeroesEntity)
}