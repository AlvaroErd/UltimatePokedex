package com.alerdoci.ultimatepokedex.data.remote.mapper

import com.alerdoci.ultimatepokedex.data.remote.model.PokedexResponse
import com.alerdoci.ultimatepokedex.data.remote.model.PokedexResultResponse
import com.alerdoci.ultimatepokedex.domain.model.Pokedex
import com.alerdoci.ultimatepokedex.domain.model.Result

fun pokedexResponseMapper(pokedex: PokedexResponse): Pokedex {
    return Pokedex(
        //domain <= data
        count = pokedex.count,
        next = pokedex.next,
        previous = pokedex.previous,
        results = pokedex.results.map { resultResponseMapper(it) },
    )
}

fun resultResponseMapper(result: PokedexResultResponse): Result {
    return Result(
        name = result.name,
        url = result.url,
    )
}