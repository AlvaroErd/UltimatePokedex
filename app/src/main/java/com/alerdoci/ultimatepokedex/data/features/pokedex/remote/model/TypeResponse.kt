package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

data class TypeResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ResultX>
)