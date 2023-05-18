package com.alerdoci.ultimatepokedex.domain.models.features.pokemon

import com.google.gson.annotations.SerializedName

data class ModelStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: ModelStatX
)