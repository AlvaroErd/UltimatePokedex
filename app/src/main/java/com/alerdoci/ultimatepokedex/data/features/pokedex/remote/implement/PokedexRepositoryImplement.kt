package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.implement

import com.alerdoci.ultimatepokedex.data.features.pokedex.mappers.toDomain
import com.alerdoci.ultimatepokedex.data.features.pokemon.mappers.toDomain
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.mappers.toDomain
import com.alerdoci.ultimatepokedex.data.remote.service.PokedexService
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelPokedexList
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelPokemonDescription
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PokedexRepositoryImplement @Inject constructor(
    private val remoteService: PokedexService,
) : PokedexRepository {

    override suspend fun getPokedex(limit: Int, offset: Int): Flow<List<ModelPokedexList>> = flow {
        val pokedex = remoteService.getPokedex(limit, offset)

        if (pokedex.isSuccessful) {
            emit(pokedex.body()?.results?.map { pokemon -> pokemon.toDomain() } ?: emptyList())
        } else {
            emit(emptyList())
        }

    }

    override suspend fun getPokemon(pokemonName: String): Flow<ModelPokemon> = flow {

        val pokemon = remoteService.getPokemon(pokemonName)

        if (pokemon.isSuccessful) {
            pokemon.body()?.let { pokemonResponse ->
                emit(pokemonResponse.toDomain())
            }
        }
    }

    override suspend fun getPokemonDescription(pokemonName: String): Flow<ModelPokemonDescription> =
        flow {

            val pokemonDescription = remoteService.getPokemonDescription(pokemonName)

            if (pokemonDescription.isSuccessful) {
                pokemonDescription.body()?.let { pokemonResponseDescription ->
                    emit(pokemonResponseDescription.toDomain())
                }
            }
        }
}