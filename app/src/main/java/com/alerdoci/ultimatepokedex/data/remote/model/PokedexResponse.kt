package com.alerdoci.ultimatepokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokedexResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: Any,
    @SerializedName("results") val results: List<PokedexResultResponse>
)