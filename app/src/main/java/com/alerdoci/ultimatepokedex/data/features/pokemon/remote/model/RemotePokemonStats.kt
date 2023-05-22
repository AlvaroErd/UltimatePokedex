package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("base_stats") val base_stats: Int,
    @SerializedName("stat") val stat: Stat
)