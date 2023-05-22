package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonOther(
    @SerializedName("official-artwork") val official_artwork: RemotePokemonOfficialArtwork?
)