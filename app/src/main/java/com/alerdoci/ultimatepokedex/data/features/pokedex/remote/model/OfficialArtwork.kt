package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)