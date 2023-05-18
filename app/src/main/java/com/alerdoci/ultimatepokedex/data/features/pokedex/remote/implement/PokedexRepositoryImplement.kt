package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.implement

import com.alerdoci.ultimatepokedex.data.features.pokedex.mappers.toDomain
import com.alerdoci.ultimatepokedex.data.remote.service.PokedexService
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PokedexRepositoryImplement @Inject constructor(
    private val remoteService: PokedexService,
) : PokedexRepository {

    override suspend fun getPokedex(limit: Int, offset: Int): Flow<List<ModelListPokedex>> = flow {
        val pokedex = remoteService.getPokedex(limit, offset)

        if (pokedex.isSuccessful) {
            emit(pokedex.body()?.results?.map { pokemon -> pokemon.toDomain() } ?: emptyList())
        } else {
            emit(emptyList())
        }
    }

//    override suspend fun getPokemon(name: String): Resource<Pokemon> {
//        val response = try {
//            api.getPokemonInfo(name)
//        }catch (e: Exception){
//            return Resource.Error("Unknown Error")
//        }
//        return Resource.Success(response)
//    }
}