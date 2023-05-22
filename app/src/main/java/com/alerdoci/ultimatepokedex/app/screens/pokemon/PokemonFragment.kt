package com.alerdoci.ultimatepokedex.app.screens.pokemon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.alerdoci.ultimatepokedex.app.common.extensions.Extensions.capitalized
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
                                loadPokemon()
                            }
                        }

                        is ResourceState.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadPokemon() {
        this.binding?.apply {
            this.tvName.text = currentPokemon?.name?.capitalized()
            this.tvId.text = currentPokemon?.id.toString()
            this.ivPokemonImage.load(currentPokemon)

            this.ivPokemonImage.load(currentPokemon.let {
                it?.sprites?.other?.official_artwork?.front_default
            })

            this.tvType1.text = currentPokemon?.let {
                it.types[0].type.name.capitalized()
            }

            if ((currentPokemon?.types?.size ?: 0) > 1) {
                tvType2.text = currentPokemon?.let {
                    it.types[1].type.name.capitalized()
                }
                tvType2.visibility = View.VISIBLE
            } else {
                tvType2.visibility = View.GONE
            }

            this.tvWeight.text = "${currentPokemon?.weight?.div(10f)} kg"
            this.tvHeight.text = "${currentPokemon?.height?.div(10f)} m"

            this.tvHp.text = currentPokemon?.let { it.stats[0].base_stats.toString() }
            this.tvAttack.text = currentPokemon?.let { it.stats[1].base_stats.toString() }
            this.tvDefense.text = currentPokemon?.let { it.stats[2].base_stats.toString() }
            this.tvSpAtk.text = currentPokemon?.let { it.stats[3].base_stats.toString() }
            this.tvSpDef.text = currentPokemon?.let { it.stats[4].base_stats.toString() }
            this.tvSpeed.text = currentPokemon?.let { it.stats[5].base_stats.toString() }

            pbHp.progress = currentPokemon?.let { it.stats[0].base_stats } ?: 0
            pbAttack.progress = currentPokemon?.let { it.stats[1].base_stats } ?: 0
            pbDefense.progress = currentPokemon?.let { it.stats[2].base_stats } ?: 0
            pbSpAtk.progress = currentPokemon?.let { it.stats[3].base_stats } ?: 0
            pbSpDef.progress = currentPokemon?.let { it.stats[4].base_stats } ?: 0
            pbSpeed.progress = currentPokemon?.let { it.stats[5].base_stats } ?: 0
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
        this.currentPokemon = null
    }
}