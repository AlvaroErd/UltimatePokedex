package com.alerdoci.ultimatepokedex.data.remote.service

import com.alerdoci.ultimatepokedex.data.remote.model.PokedexResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokedex(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokedexResponse

}