package org.demre.certificacion.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import coil.load
import org.demre.certificacion.R
import org.demre.certificacion.databinding.FragmentDetalleBinding
import org.demre.certificacion.model.local.DetalleHeroesEntity
import org.demre.certificacion.viewModel.ViewModel

class DetalleFragment : Fragment() {

    private lateinit var binding: FragmentDetalleBinding
    private val viewModel: ViewModel by activityViewModels()
    private var heroeId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)

        arguments?.getString("heroeId")?.let { id ->
            heroeId = id
            viewModel.getDetallesHeroeIdInternet(id)
        }

        viewModel.getDetalleHeroes().observe(viewLifecycleOwner) { comicDetalle ->
            comicDetalle?.let {

                configuracionVistas(binding, it)
                configuracionBotonEmail(binding, it)
            }
        }

        return binding.root
    }

    private fun configuracionVistas(
        binding: FragmentDetalleBinding, heroeDetalle: DetalleHeroesEntity
    ) {
        binding.tvNombreDetalle.text = heroeDetalle.nombre
        binding.ivImagenDetalle.load(heroeDetalle.imagenLink)
        binding.tvOrigen.text = getString(R.string.origen, heroeDetalle.origen)
        binding.tvPoder.text = getString(R.string.poder, heroeDetalle.poder)
        binding.tvCreacion.text = getString(R.string.creacion, heroeDetalle.creacion.toString())
        binding.tvColor.text = getString(R.string.color, heroeDetalle.color)
        binding.tvTraduccion.text =
            getString(if (heroeDetalle.traduccion) R.string.con_traduccion else R.string.sin_traduccion)
    }

    private fun configuracionBotonEmail(
        binding: FragmentDetalleBinding, heroeDetalle: DetalleHeroesEntity
    ) {
        binding.btnMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {

                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("Comicconchile@hotmail.com"))
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Votación ${heroeDetalle.nombre}"
                )
                putExtra(
                    Intent.EXTRA_TEXT, getString(
                        R.string.email_body_template,
                        heroeDetalle.nombre
                    )
                )
            }

            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}