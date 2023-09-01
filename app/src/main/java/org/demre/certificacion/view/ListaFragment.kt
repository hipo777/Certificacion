package org.demre.certificacion.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.demre.certificacion.R
import org.demre.certificacion.databinding.FragmentListaBinding
import org.demre.certificacion.viewModel.ViewModel

class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentListaBinding.inflate(layoutInflater, container, false)

        val adapter = Adapter()
        binding.rvLista.adapter = adapter
        viewModel.getListaHeroes().observe(viewLifecycleOwner) { listaHeroes ->

            listaHeroes?.let {
                adapter.update(it)
            }
        }

        adapter.SuperHeroeSeleccionado().observe(viewLifecycleOwner) { seleccionHeroe ->

            val bundle = Bundle().apply {
                putString("heroeId", seleccionHeroe.id)
            }

            findNavController().navigate(R.id.action_listaFragment_to_detalleFragment, bundle)
        }

        return binding.root
    }
}
