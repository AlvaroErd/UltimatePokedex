package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class EffectEntry(
    val effect: String,
    val language: Language,
    @SerializedName("short_effect")
    val shortEffect: String
)