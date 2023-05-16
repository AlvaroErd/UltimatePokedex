package com.alerdoci.ultimatepokedex.data.remote.models

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)