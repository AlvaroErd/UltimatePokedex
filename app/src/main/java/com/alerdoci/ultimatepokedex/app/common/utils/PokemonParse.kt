package com.alerdoci.ultimatepokedex.app.common.utils

import android.view.View
import com.alerdoci.ultimatepokedex.R

fun setLinearBackground(type: String?, view: View) {
    when (type) {
        "poison" -> view.setBackgroundResource(R.color.poison)
        "grass" -> view.setBackgroundResource(R.color.grass)
        "fire" -> view.setBackgroundResource(R.color.fire)
        "normal" -> view.setBackgroundResource(R.color.normal)
        "flying" -> view.setBackgroundResource(R.color.flying)
        "rock" -> view.setBackgroundResource(R.color.rock)
        "water" -> view.setBackgroundResource(R.color.water)
        "ice" -> view.setBackgroundResource(R.color.ice)
        "ghost" -> view.setBackgroundResource(R.color.ghost)
        "steel" -> view.setBackgroundResource(R.color.steel)
        "physic" -> view.setBackgroundResource(R.color.physic)
        "dark" -> view.setBackgroundResource(R.color.dark)
        "fairy" -> view.setBackgroundResource(R.color.fairy)
        "fighting" -> view.setBackgroundResource(R.color.fighting)
        "ground" -> view.setBackgroundResource(R.color.ground)
        "bug" -> view.setBackgroundResource(R.color.bug)
        "electric" -> view.setBackgroundResource(R.color.electric)
        "dragon" -> view.setBackgroundResource(R.color.dragon)
        else -> {
            view.setBackgroundResource(R.color.normal)
        }
    }
}