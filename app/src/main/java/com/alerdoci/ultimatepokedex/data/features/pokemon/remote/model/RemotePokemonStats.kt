package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonStats(
    @SerializedName("base_stat") val base_stats: Int,
    @SerializedName("stat") val stat: RemotePokemonStat
)