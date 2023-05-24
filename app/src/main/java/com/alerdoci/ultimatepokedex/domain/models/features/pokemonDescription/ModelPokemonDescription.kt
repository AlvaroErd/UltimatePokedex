package com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription

data class ModelPokemonDescription(
    val name: String,
    val flavorTextEntries: List<ModelFlavorTextEntry>,
    val genera: List<ModelGenera>,
    val generation: ModelGeneration,
    val names: List<ModelNames>,
    val shape: ModelShape,
)
