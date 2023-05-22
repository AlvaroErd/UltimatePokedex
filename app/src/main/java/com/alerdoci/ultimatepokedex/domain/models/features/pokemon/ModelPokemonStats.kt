package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

data class ModelPokemonStats(
    val base_stats: Int? = null,
    val stat: List<ModelPokemonStat>
)