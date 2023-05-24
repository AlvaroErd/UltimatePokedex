package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteNames(
    @SerializedName("language")
    val language: RemoteLanguage,
    @SerializedName("name")
    val name: String
)