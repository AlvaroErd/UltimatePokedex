package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGenera(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: RemoteLanguage
)