package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("other") val other: Other,
)