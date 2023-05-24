package com.alerdoci.ultimatepokedex.data.features.pokemonDescription.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePalParkEncounter(
    @SerializedName("area")
    val area: RemoteArea,
    @SerializedName("base_score")
    val baseScore: Int,
    @SerializedName("rate")
    val rate: Int
)