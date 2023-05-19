package com.alerdoci.ultimatepokedex.domain.usecases

import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokedexRepository
) {

    suspend operator fun invoke(pokemonName: String): Flow<ModelPokemon> =
        repository.getPokemon(pokemonName)
}