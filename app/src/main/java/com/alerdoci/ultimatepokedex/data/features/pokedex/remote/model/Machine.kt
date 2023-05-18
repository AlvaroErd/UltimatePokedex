package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.google.gson.annotations.SerializedName

data class Machine(
    val machine: MachineX,
    @SerializedName("version_group")
    val versionGroup: VersionGroupXX
)