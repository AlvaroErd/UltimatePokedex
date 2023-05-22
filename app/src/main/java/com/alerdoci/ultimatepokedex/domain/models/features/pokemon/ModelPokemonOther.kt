package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork") val official_artwork: OfficialArtwork
)