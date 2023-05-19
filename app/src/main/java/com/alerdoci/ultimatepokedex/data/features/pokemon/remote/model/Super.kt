package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class Super(
    @SerializedName("use_after")
    val useAfter: List<Any>,
    @SerializedName("use_before")
    val useBefore: Any
)