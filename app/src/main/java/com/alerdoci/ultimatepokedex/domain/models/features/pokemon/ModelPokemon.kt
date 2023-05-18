package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

data class ModelPokemon(
    val name: String = "",
    val imageUrl: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<String> = emptyList(),
    val moves: List<String> = emptyList(),
    val stats: List<String> = emptyList(),
    val abilities: List<String> = emptyList(),
    val id: Int? = null,
    val isFavorite: Boolean = false
)