package com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemon(
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("sprites") val sprites: List<Sprites>,
    @SerializedName("types") val types: List<TypeResponse>,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("weight") var weight: String? = null,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("description") var description: String,
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight!!.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height!!.toFloat() / 10)
}