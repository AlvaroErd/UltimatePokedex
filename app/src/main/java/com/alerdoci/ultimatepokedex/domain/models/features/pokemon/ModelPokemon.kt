package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

data class ModelPokemon(
    val name: String? = null,
    val id: String? = null,
    val sprites: ModelPokemonSprites?,
//    val types: List<ModelPokemonTypeResponse>,
//    val height: String? = null,
//    val weight: String? = null,
//    val stats: List<ModelPokemonStats>,
    val description: String = "",
)