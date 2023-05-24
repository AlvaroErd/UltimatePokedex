package com.alerdoci.ultimatepokedex.data.remote.service

import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model.RemotePokedex
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemon
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemotePokemonDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokedex(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<RemotePokedex>

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") pokemonName: String
    ): Response<RemotePokemon>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonDescription(
        @Path("name") pokemonName: String
    ): Response<RemotePokemonDescription>

}