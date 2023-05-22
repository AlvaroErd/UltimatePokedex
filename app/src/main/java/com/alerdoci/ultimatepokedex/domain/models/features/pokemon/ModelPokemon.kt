package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

data class ModelPokemon(
    val name: String? = null,
    val id: Int? = null,
    val sprites: List<Sprites>,
    val types: List<TypeResponse>,
    val height: Int? = null,
    val weight: String? = null,
    val stats: List<Stats>,
    val description: String,
)