package com.alerdoci.ultimatepokedex.domain.models.features.pokedex

data class ModelPokedex(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ModelPokedexList>
)