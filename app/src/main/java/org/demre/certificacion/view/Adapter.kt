package org.demre.certificacion.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.demre.certificacion.databinding.ItemListaBinding
import org.demre.certificacion.model.local.ListaHeroesEntity

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listaHeroes = listOf<ListaHeroesEntity>()
    private val heroeSeleccionado = MutableLiveData<ListaHeroesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {

        return ViewHolder(ItemListaBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val heroe = listaHeroes[position]
        holder.bind(heroe)
    }

    override fun getItemCount(): Int = listaHeroes.size

    fun update(miLista: List<ListaHeroesEntity>) {
        listaHeroes = miLista
        notifyItemChanged(0, itemCount)
    }

    fun SuperHeroeSeleccionado(): LiveData<ListaHeroesEntity> = heroeSeleccionado

    inner class ViewHolder(private val binding: ItemListaBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(heroe: ListaHeroesEntity) {
            binding.ivImagenLista.load(heroe.imagenLink)
            binding.tvNombre.text = heroe.nombre
            binding.tvPoder.text = heroe.poder
            binding.tvCreacion.text = heroe.creacion.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

            heroeSeleccionado.value = listaHeroes[adapterPosition]
        }
    }
}
