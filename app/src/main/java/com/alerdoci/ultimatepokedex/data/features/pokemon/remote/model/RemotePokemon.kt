package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemon(
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("sprites") val sprites: RemotePokemonSprites?,
    @SerializedName("types") val types: List<RemotePokemonTypeResponse>,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("stats") val stats: List<RemotePokemonStats>,
) {
    fun getIdString(): String = String.format("#%03d", id ?: 0)
}