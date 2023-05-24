package com.alerdoci.ultimatepokedex.domain.repository

import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelPokedexList
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelPokemonDescription
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {

    suspend fun getPokedex(limit: Int, offset: Int): Flow<List<ModelPokedexList>>

    suspend fun getPokemon(pokemonName: String): Flow<ModelPokemon>

    suspend fun getPokemonDescription(pokemonName: String): Flow<ModelPokemonDescription>

}