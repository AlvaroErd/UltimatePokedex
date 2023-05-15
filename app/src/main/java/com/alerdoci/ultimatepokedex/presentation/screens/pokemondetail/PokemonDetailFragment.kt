package com.alerdoci.ultimatepokedex.presentation.screens.pokemondetail

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    companion object {
        fun newInstance(animalId: Long) = PokemonDetailFragment()
//            .apply { this.pokemonId = pokemonId }
    }

}