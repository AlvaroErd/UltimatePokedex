package com.alerdoci.ultimatepokedex.data.features.pokedex.mappers

import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model.PokedexResponse
import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model.PokedexResultResponse
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelPokedex

fun PokedexResponse.toDomain(): ModelPokedex = ModelPokedex(
    //domain <= data
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.map { it.toDomain() },
)

fun PokedexResultResponse.toDomain(): ModelListPokedex = ModelListPokedex(
    name = this.name,
    url = this.url,
    imageUrl = this.getImageUrl(),
    pokemonNumber = this.getPokemonNumber()
)
