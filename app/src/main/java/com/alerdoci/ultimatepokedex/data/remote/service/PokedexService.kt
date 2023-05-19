package com.alerdoci.ultimatepokedex.data.remote.service

import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model.PokedexResponse
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokedex(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokedexResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") pokemonName: String
    ): Response<PokemonResponse>
}