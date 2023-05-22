package com.alerdoci.ultimatepokedex.data.features.pokemon.mappers

import com.alerdoci.ultimatepokedex.data.features.pokedex.mappers.toDomain
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemon
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon

fun RemotePokemon.toDomain(): ModelPokemon = ModelPokemon(
        name = this.name,
        id = this.id,
        sprites = this.sprites.map { it.toDomain() },
        types = this.types.map { it.toDomain() },
        height = this.getHeightString().toInt(),
        weight = this.getWeightString(),
        stats = this.stats.map { it.toDomain() },
        description = this.description
)

fun RemotePokemonSprites.toDomain(): ModelPokemonSprite = ModelPokemonSprite(
        other = this.other.map { it.toDomain() },
)

fun RemotePokemonOther.toDomain(): ModelPokemonOther = ModelPokemonOther(
        official_artwork = this.official_artwork.map { it.toDomain() },
)

fun RemotePokemonOfficialArtwork.toDomain(): ModelPokemonOfficialArtwork =
        ModelPokemonOfficialArtwork(
                front_default = this.front_default,
        )

fun RemotePokemonTypeResponse.toDomain(): ModelPokemonTypeResponse = ModelPokemonTypeResponse(
        slot = this.slot,
        type = this.type.map { it.toDomain() },
)

fun RemotePokemonType.toDomain(): ModelPokemonType = ModelPokemonType(
        url = this.url,
        name = this.name,
)

fun RemotePokemonStat.toDomain(): ModelPokemonStat = ModelPokemonStat(
        stat = this.stat.map { it.toDomain() },
)

fun RemotePokemonStats.toDomain(): ModelPokemonStats = ModelPokemonStats(
        base_stats = this.base_stats,
        stat = this.stat
)
