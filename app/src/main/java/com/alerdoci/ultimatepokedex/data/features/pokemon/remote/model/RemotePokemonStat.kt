package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonStat(
    @SerializedName("name") val name: String,
)