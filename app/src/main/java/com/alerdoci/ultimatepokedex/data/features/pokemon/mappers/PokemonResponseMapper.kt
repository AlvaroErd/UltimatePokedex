package com.alerdoci.ultimatepokedex.data.features.pokemon.mappers

import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemon
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonOfficialArtwork
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonOther
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonSprites
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonStat
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonStats
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonType
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.RemotePokemonTypeResponse
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemon
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonOfficialArtwork
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonOther
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonSprites
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonStat
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonStats
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonType
import com.alerdoci.ultimatepokedex.domain.models.features.pokemon.ModelPokemonTypeResponse

fun RemotePokemon.toDomain(): ModelPokemon = ModelPokemon(
        name = this.name,
        id = this.getIdString(),
        sprites = this.sprites
//        types = this.types.map { it.toDomain() },
//        height = this.getHeightString(),
//        weight = this.getWeightString(),
//        stats = this.stats.map { it.toDomain() },
//        description = this.description
)

fun RemotePokemonSprites.toDomain(): ModelPokemonSprites = ModelPokemonSprites(
        other = this.other.map { it.toDomain() },
)

fun RemotePokemonOther.toDomain(): ModelPokemonOther = ModelPokemonOther(
        official_artwork = this.official_artwork.map { it.toDomain() },
)

fun RemotePokemonOfficialArtwork.toDomain(): ModelPokemonOfficialArtwork =
        ModelPokemonOfficialArtwork(front_default = this.front_default)

fun RemotePokemonTypeResponse.toDomain(): ModelPokemonTypeResponse = ModelPokemonTypeResponse(
        slot = this.slot,
        type = this.type.map { it.toDomain() },
)

fun RemotePokemonType.toDomain(): ModelPokemonType = ModelPokemonType(
        url = this.url,
        name = this.name,
)

fun RemotePokemonStats.toDomain(): ModelPokemonStats = ModelPokemonStats(
        base_stats = this.base_stats,
        stat = this.stat.map { it.toDomain() },
)

fun RemotePokemonStat.toDomain(): ModelPokemonStat = ModelPokemonStat(
        name = this.name
)
