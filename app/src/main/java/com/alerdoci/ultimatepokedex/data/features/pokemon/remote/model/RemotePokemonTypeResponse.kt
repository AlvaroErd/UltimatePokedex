package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonTypeResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: List<RemotePokemonType>
)