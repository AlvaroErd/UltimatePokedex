package com.alerdoci.ultimatepokedex.app.common.extensions

object Extensions {

    fun String.capitalized(): String {
        return lowercase().replaceFirstChar { it.titlecase() }
    }

}