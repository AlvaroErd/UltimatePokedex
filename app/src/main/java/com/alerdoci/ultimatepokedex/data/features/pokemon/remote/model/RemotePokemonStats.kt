package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonStats(
    @SerializedName("base_stats") val base_stats: Int,
    @SerializedName("stat") val stat: List<RemotePokemonStat>
)