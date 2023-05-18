package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow
)