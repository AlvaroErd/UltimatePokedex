package com.alerdoci.ultimatepokedex.app.screens.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.ImageLoader
import com.alerdoci.ultimatepokedex.app.common.states.ResourceState
import com.alerdoci.ultimatepokedex.app.screens.pokemon.viewmodel.PokemonViewModel
import com.alerdoci.ultimatepokedex.databinding.FragmentPokemonBinding
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    companion object {
        fun newInstance(pokemonName: String) = PokemonFragment()
            .apply { this.pokemonName = pokemonName }
    }

    var pokemonName: String = "pikachu"

    private val viewModel: PokemonViewModel by viewModels()
    private var binding: FragmentPokemonBinding? = null
    private var currentPokemon: ModelPokemon? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        this.binding = FragmentPokemonBinding.inflate(inflater)
        viewModel.loadPokemon(pokemonName)
        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.currentPokemon.collectLatest { pokemonState ->
                    when (pokemonState) {
                        is ResourceState.Loading -> {}
                        is ResourceState.Success -> {
                            currentPokemon = pokemonState.data as ModelPokemon
                            withContext(Dispatchers.Main) {
                                loadPokemon(pokemonName)
                            }
                        }

                        is ResourceState.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    private fun loadPokemon(pokemonName: String) {
        this.binding?.apply {
            val imageLoader = ImageLoader(requireContext())
            this.tv.text = currentPokemon?.name
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
        this.currentPokemon = null
    }
}