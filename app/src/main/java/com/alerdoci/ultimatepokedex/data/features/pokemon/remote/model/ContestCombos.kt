package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class ContestCombos(
    val normal: Normal,
    @SerializedName("super")
    val supe: Super
)