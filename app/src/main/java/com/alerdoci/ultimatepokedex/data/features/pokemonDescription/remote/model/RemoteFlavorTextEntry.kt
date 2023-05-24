package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteFlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: RemoteLanguage,
    @SerializedName("version")
    val version: RemoteVersion
)