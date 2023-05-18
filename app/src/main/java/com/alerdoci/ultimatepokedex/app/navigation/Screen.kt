package com.alerdoci.ultimatepokedex.app.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Pokedex : Screen(route = "pokedex_screen")
    object Favorites : Screen(route = "favorites_screen")
    object Types : Screen(route = "types_screen")
    object Moves : Screen(route = "moves_screen")

//    // If someday i need to pass args
//    fun navArgs(vararg args: String): String {
//        return buildString {
//            append(route)
//            args.forEach { arg ->
//                append("/$arg")
//            }
//        }
//    }
//
//    // Example for Pokemon Screen
//    object Pokemon : Screen(route = "pokemon_screen")

}