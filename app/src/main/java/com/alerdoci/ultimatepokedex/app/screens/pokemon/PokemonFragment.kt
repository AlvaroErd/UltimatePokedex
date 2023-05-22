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
import coil.load
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
                                loadPokemon(pokemon = ModelPokemon())
                            }
                        }
                        is ResourceState.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    private fun loadPokemon(pokemon: ModelPokemon) {
        this.binding?.apply {

            this.tvName.text = pokemon.name
            this.tvId.text = pokemon.id.toString()

            this.ivPokemonImage.load(pokemon.sprites.other.official_artwork.front_default)
            this.ivBackgroundImage    //Add tint in base at type

            this.tvType1.text = pokemon.types[1].type.name

            if (pokemon.types.size > 1) {
                tvType2.text = pokemon.types[1].type.name
                tvType2.visibility = View.VISIBLE
            } else {
                tvType2.visibility = View.GONE
            }

            this.tvDescription.text = pokemon.description

            this.tvHeight.text = pokemon.height.toString()
            this.textViewWeight.text = pokemon.weight.toString()


            this.tvHp.text = pokemon.stats[0].base_stats.toString()
            this.tvAttack.text = pokemon.stats[1].base_stats.toString()
            this.tvDefense.text = pokemon.stats[2].base_stats.toString()
            this.tvSpAtk.text = pokemon.stats[3].base_stats.toString()
            this.tvSpDef.text = pokemon.stats[4].base_stats.toString()
            this.tvSpeed.text = pokemon.stats[5].base_stats.toString()
            this.tvTotal.text = pokemon.stats[6].base_stats.toString()

            pbHp.progress = pokemon.stats[0].base_stats
            pbAttack.progress = pokemon.stats[1].base_stats
            pbDefense.progress = pokemon.stats[2].base_stats
            pbSpAtk.progress = pokemon.stats[3].base_stats
            pbSpDef.progress = pokemon.stats[4].base_stats
            pbSpeed.progress = pokemon.stats[5].base_stats
            pbTotal.progress = pokemon.stats[6].base_stats

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
        this.currentPokemon = null
    }
}