package com.alerdoci.ultimatepokedex.app.common.utils

import androidx.compose.ui.graphics.Color
import com.alerdoci.ultimatepokedex.app.theme.typeBug
import com.alerdoci.ultimatepokedex.app.theme.typeDark
import com.alerdoci.ultimatepokedex.app.theme.typeDragon
import com.alerdoci.ultimatepokedex.app.theme.typeElectric
import com.alerdoci.ultimatepokedex.app.theme.typeFairy
import com.alerdoci.ultimatepokedex.app.theme.typeFighting
import com.alerdoci.ultimatepokedex.app.theme.typeFire
import com.alerdoci.ultimatepokedex.app.theme.typeFlying
import com.alerdoci.ultimatepokedex.app.theme.typeGhost
import com.alerdoci.ultimatepokedex.app.theme.typeGrass
import com.alerdoci.ultimatepokedex.app.theme.typeGround
import com.alerdoci.ultimatepokedex.app.theme.typeIce
import com.alerdoci.ultimatepokedex.app.theme.typeNormal
import com.alerdoci.ultimatepokedex.app.theme.typePoison
import com.alerdoci.ultimatepokedex.app.theme.typePsychic
import com.alerdoci.ultimatepokedex.app.theme.typeRock
import com.alerdoci.ultimatepokedex.app.theme.typeSteel
import com.alerdoci.ultimatepokedex.app.theme.typeWater
import com.alerdoci.ultimatepokedex.data.features.pokemon.remote.model.Type
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when (type.name.lowercase(Locale.ROOT)) {
        "normal" -> typeNormal
        "fire" -> typeFire
        "water" -> typeWater
        "electric" -> typeElectric
        "grass" -> typeGrass
        "ice" -> typeIce
        "fighting" -> typeFighting
        "poison" -> typePoison
        "ground" -> typeGround
        "flying" -> typeFlying
        "psychic" -> typePsychic
        "bug" -> typeBug
        "rock" -> typeRock
        "ghost" -> typeGhost
        "dragon" -> typeDragon
        "dark" -> typeDark
        "steel" -> typeSteel
        "fairy" -> typeFairy
        else -> Color.Black
    }
}