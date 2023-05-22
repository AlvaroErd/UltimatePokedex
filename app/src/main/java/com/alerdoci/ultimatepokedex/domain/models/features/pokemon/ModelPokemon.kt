package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

data class ModelPokemon(
    var name: String? = null,
    var id: Int? = null,
    val sprites: List<Sprites>,
    val types: List<TypeResponse>,
    var height: Int? = null,
    var weight: String? = null,
    val stats: List<Stats>,
    var description: String,
)