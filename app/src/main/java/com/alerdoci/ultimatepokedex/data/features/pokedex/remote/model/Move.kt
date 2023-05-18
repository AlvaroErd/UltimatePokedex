package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class Move(
    val move: MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)