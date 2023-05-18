package com.alerdoci.ultimatepokedex.domain.repository

import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {

    suspend fun getPokedex(limit: Int, offset: Int): Flow<List<ModelListPokedex>>

    //suspend fun getPokemon(name: String): Flow<Pokemon>
}