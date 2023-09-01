package org.demre.certificacion.model.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class ListaHeroesDatabaseTest {

    private lateinit var database: DatabaseHeroe
    private lateinit var dao: DaoHeroe

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, DatabaseHeroe::class.java)
            .allowMainThreadQueries() // Permitir consultas en el hilo principal para pruebas
            .build()
        dao = database.getHeroesDao()
    }

    @After
    fun closeDatabase() {

        database.close()
    }

    @Test
    fun insertAndGetListaHeroes() = runBlocking {
        // Given
        val heroe1 = ListaHeroesEntity("1", "Batman", "Sin origen", "imagen1.jpg","Sin poder",33 )
        val heroe2 = ListaHeroesEntity("2", "Superman", "Krypton", "imagen2.jpg", "Con poder",44)
        val listaHeroes = listOf(heroe1, heroe2)

        // When
        dao.insertListaHeroes(listaHeroes)
        val savedHeroes = getValue(dao.getListaHeroes())

        // Then
        assertThat(savedHeroes.size).isEqualTo(listaHeroes.size)
        for (i in listaHeroes.indices) {
            assertThat(savedHeroes[i]).isEqualTo(listaHeroes[i])
        }
    }

    private fun <T> getValue(liveData: LiveData<T>): T {
        val data = mutableListOf<T>()
        val observer = Observer<T> { value -> data.add(value) }
        liveData.observeForever(observer)
        liveData.removeObserver(observer)
        return data[0]
    }
}
