package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonOfficialArtwork(
    @SerializedName("front_default") val front_default: String
)