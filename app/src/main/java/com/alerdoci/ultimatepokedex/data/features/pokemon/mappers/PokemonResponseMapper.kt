package com.alerdoci.ultimatepokedex.data.features.pokemon.mappers

import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model.PokemonResponse
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon

fun PokemonResponse.toDomain(): ModelPokemon = ModelPokemon(
        name = this.name,
        imageUrl = this.sprites.other.dreamWorld.frontDefault,
        height = this.height,
        types = this.types.map { it.type.name },
        weight = this.weight,
        moves = this.moves.map { it.move.name },
        stats = this.stats.map { it.stat.name },
        abilities = this.abilities.map { it.ability.name },
        id = this.id
)
