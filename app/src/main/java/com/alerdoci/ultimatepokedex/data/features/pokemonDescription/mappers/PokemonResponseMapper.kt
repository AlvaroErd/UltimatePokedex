package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.mappers

import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteFlavorTextEntry
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteGenera
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteGeneration
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteLanguage
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteNames
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemotePokemonDescription
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteShape
import com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model.RemoteVersion
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelFlavorTextEntry
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelGenera
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelGeneration
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelLanguage
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelNames
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelPokemonDescription
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelShape
import com.alerdoci.ultimatepokedex.domain.models.features.pokemonDescription.ModelVersion


fun RemotePokemonDescription.toDomain(): ModelPokemonDescription = ModelPokemonDescription(
    // https://jsonviewer.stack.hu/
    // List [ ] .map { it.toDomain() },
    // Array { } .toDomain(),

    flavorTextEntries = this.flavorTextEntries.map { it.toDomain() },
    genera = this.genera.map { it.toDomain() },
    generation = this.generation.toDomain(),
    names = this.names.map { it.toDomain() },
    shape = this.shape.toDomain(),
    name = this.name
)

fun RemoteFlavorTextEntry.toDomain(): ModelFlavorTextEntry = ModelFlavorTextEntry(
    flavorText = this.flavorText,
    language = this.language.toDomain(),
    version = this.version.toDomain()
)

fun RemoteVersion.toDomain(): ModelVersion = ModelVersion(
    name = this.name,
    url = this.url
)

fun RemoteGenera.toDomain(): ModelGenera = ModelGenera(
    genus = this.genus,
    language = this.language.toDomain(),
)

fun RemoteLanguage.toDomain(): ModelLanguage = ModelLanguage(
    name = this.name,
    url = this.url
)

fun RemoteGeneration.toDomain(): ModelGeneration = ModelGeneration(
    name = this.name,
    url = this.url
)

fun RemoteNames.toDomain(): ModelNames = ModelNames(
    name = this.name,
    language = this.language.toDomain()
)

fun RemoteShape.toDomain(): ModelShape = ModelShape(
    name = this.name,
    url = this.url
)
