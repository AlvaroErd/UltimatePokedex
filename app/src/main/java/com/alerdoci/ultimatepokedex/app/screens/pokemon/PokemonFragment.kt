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
import com.alerdoci.ultimatepokedex.app.common.utils.setLinearBackground
import com.alerdoci.ultimatepokedex.app.screens.pokemon.viewmodel.PokemonViewModel
import com.alerdoci.ultimatepokedex.databinding.FragmentPokemonBinding
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelPokemonDescription
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
    private var currentPokemonDescription: ModelPokemonDescription? = null

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

        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch(Dispatchers.IO) {

//                        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//
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

                viewModel.currentPokemonDescription.collectLatest { pokemonStateDescription ->
                    when (pokemonStateDescription) {
                        is ResourceState.Loading -> {}
                        is ResourceState.Success -> {
                            currentPokemonDescription =
                                pokemonStateDescription.data as ModelPokemonDescription
                            withContext(Dispatchers.Main) {
                                loadPokemonDescription()
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
            currentPokemon?.let { pokemon ->
                tvName.text =
                    pokemon.name?.capitalized()
                tvId.text =
                    pokemon.id.toString()
                ivPokemonImage.load(pokemon.sprites?.other?.official_artwork?.front_default)
                pokemon.types.let { types ->

                    tvType1.text =
                        types.getOrNull(0)?.type?.name?.capitalized()
                    val typeColor1 = types.getOrNull(0)?.type?.name
                    setLinearBackground(typeColor1, tvType1)

                    tvType2.text = types.getOrNull(1)?.type?.name?.capitalized()
                    val typeColor2 = types.getOrNull(1)?.type?.name
                    setLinearBackground(typeColor2, tvType2)

                    val typeColorBg = types.getOrNull(0)?.type?.name
                    setLinearBackground(typeColorBg, ivBackgroundImage)
                }

                tvType2.visibility = if ((pokemon.types.size) > 1) View.VISIBLE else View.GONE
                val baseStats = pokemon.stats.map { it.base_stats.toString() }
                val textViews = listOf(tvHp, tvAttack, tvDefense, tvSpAtk, tvSpDef, tvSpeed)
                val progressBars = listOf(pbHp, pbAttack, pbDefense, pbSpAtk, pbSpDef, pbSpeed)
                for (i in 0 until 6) {
                    textViews[i].text = baseStats[i]
                    progressBars[i].progress = baseStats[i].toInt()
                }
                tvWeight.text = "${pokemon.weight?.div(10f)} kg"
                tvHeight.text = "${pokemon.height?.div(10f)} m"
            }
        }
//        currentPokemonDescription?.let { pokemonDescription ->
//            tvNameOutlined.text = pokemonDescription.name
//        }
    }

    private fun loadPokemonDescription() {
        this.binding?.apply {
            currentPokemonDescription?.let { pokemonDescription ->
                tvNameOutlined.text = pokemonDescription.names[0].name
            }

            currentPokemonDescription?.let { pokemonDescription ->
                tvDescription.text =
                    pokemonDescription.flavorTextEntries[0].flavorText.replace("\n", " ").replace(
                        "POKéMON",
                        "Pokémon"
                    ) + " " + pokemonDescription.flavorTextEntries[2].flavorText.replace("\n", " ")
                        .replace("POKéMON", "Pokémon")
            }

            tvShape.text = currentPokemonDescription?.shape?.name?.capitalized()

            currentPokemonDescription?.let { pokemonDescription ->
                tvType.text =
                    pokemonDescription.genera[7].genus.capitalized().replace(" pokémon", "")
            }
            tvGeneration.text = currentPokemonDescription?.generation?.name?.capitalized()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding = null
        this.currentPokemon = null
    }
}