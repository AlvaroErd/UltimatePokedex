package com.alerdoci.ultimatepokedex.domain.usecases

import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelPokemonDescription
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDescriptionUseCase @Inject constructor(
    private val repository: PokedexRepository
) {

    suspend operator fun invoke(pokemonName: String): Flow<ModelPokemonDescription> =
        repository.getPokemonDescription(pokemonName)

}