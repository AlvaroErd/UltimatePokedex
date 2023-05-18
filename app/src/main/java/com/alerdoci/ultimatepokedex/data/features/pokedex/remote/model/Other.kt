package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)