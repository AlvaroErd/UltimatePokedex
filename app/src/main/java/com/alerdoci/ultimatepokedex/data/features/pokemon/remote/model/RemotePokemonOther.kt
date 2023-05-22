package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork") val official_artwork: OfficialArtwork
)