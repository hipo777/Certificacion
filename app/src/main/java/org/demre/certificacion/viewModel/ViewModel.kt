package org.demre.certificacion.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.demre.certificacion.model.Repository
import org.demre.certificacion.model.local.DatabaseHeroe
import org.demre.certificacion.model.local.DetalleHeroesEntity
import org.demre.certificacion.model.local.ListaHeroesEntity

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio: Repository
    private val heroeDetalleLiveData = MutableLiveData<DetalleHeroesEntity>()

    init {

        val bd = DatabaseHeroe.getDatabase(application)
        val dao = bd.getHeroesDao()

        repositorio = Repository(dao)
        viewModelScope.launch {
            repositorio.getListaHeroesRed()
        }
    }

    fun getListaHeroes(): LiveData<List<ListaHeroesEntity>> =
        repositorio.listaHeroesLiveData

    fun getDetalleHeroes(): LiveData<DetalleHeroesEntity> = heroeDetalleLiveData
    fun getDetallesHeroeIdInternet(id: String) = viewModelScope.launch {

        val heroesDetalleRed = repositorio.getDetalleHeroesRed(id)
        heroesDetalleRed?.let {

            heroeDetalleLiveData.postValue(it)
        }
    }
}
