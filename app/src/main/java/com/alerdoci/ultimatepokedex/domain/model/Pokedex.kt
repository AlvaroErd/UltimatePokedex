package com.alerdoci.ultimatepokedex.domain.model

data class Pokedex(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)