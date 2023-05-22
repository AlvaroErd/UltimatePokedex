package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Type
)