package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGrowthRate(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)