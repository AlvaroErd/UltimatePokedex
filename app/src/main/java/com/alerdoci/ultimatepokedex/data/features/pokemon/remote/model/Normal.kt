package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Normal(
    @SerializedName("use_after")
    val useAfter: List<UseAfter>,
    @SerializedName("use_before")
    val useBefore: Any
)