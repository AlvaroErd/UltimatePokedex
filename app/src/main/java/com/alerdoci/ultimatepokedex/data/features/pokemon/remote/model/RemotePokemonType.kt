package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)