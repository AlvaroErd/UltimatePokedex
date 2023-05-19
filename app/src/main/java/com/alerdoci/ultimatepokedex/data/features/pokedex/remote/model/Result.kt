package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.model

import com.alerdoci.ultimatepokedex.app.common.utils.Constants.SPRITE_URL
import com.google.gson.annotations.SerializedName

data class PokedexResultResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "$SPRITE_URL$index.png"
    }

    fun getPokemonNumber(): String {
        return url.split("/".toRegex()).dropLast(1).last().format("#%03d")
    }
}