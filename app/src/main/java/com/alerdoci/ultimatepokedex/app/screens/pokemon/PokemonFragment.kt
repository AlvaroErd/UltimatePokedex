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

    private fun loadPokemon() {
        this.binding?.apply {

            this.tvName.text = currentPokemon?.name?.capitalized()!!
            this.tvId.text = currentPokemon?.id.toString()
            this.ivPokemonImage.load(currentPokemon)

            //this.ivPokemonImage.load(currentPokemon.sprites[0].other[0].official_artwork[0].front_default)
//            this.ivBackgroundImage    //Add tint in base at type

//            this.tvType1.text = currentPokemon?.types?.get(1)?.type.name
//
//            if (currentPokemon?.types.size > 1) {
//                tvType2.text = currentPokemon?.types?.get(1)?.type.name
//                tvType2.visibility = View.VISIBLE
//            } else {
//                tvType2.visibility = View.GONE
//            }

//            this.tvDescription.text = currentPokemon.description
//            this.tvHeight.text = currentPokemon.height.toString()
//            this.textViewWeight.text = currentPokemon.weight.toString()

//            this.tvHp.text = currentPokemon.stats[0].base_stats.toString()
//            this.tvAttack.text = currentPokemon.stats[1].base_stats.toString()
//            this.tvDefense.text = currentPokemon.stats[2].base_stats.toString()
//            this.tvSpAtk.text = currentPokemon.stats[3].base_stats.toString()
//            this.tvSpDef.text = currentPokemon.stats[4].base_stats.toString()
//            this.tvSpeed.text = currentPokemon.stats[5].base_stats.toString()
//            this.tvTotal.text = currentPokemon.stats[6].base_stats.toString()
//
//            pbHp.progress = currentPokemon.stats[0].base_stats!!
//            pbAttack.progress = currentPokemon.stats[1].base_stats!!
//            pbDefense.progress = currentPokemon.stats[2].base_stats!!
//            pbSpAtk.progress = currentPokemon.stats[3].base_stats!!
//            pbSpDef.progress = currentPokemon.stats[4].base_stats!!
//            pbSpeed.progress = currentPokemon.stats[5].base_stats!!
//            pbTotal.progress = currentPokemon.stats[6].base_stats!!
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
        this.currentPokemon = null
    }
}