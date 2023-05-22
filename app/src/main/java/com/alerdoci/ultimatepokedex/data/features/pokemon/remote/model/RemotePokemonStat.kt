package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("name") val name: String,
)