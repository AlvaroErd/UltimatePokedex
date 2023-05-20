package com.alerdoci.ultimatepokedex.app.navigation

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alerdoci.ultimatepokedex.app.screens.favorites.composables.FavoritesScreen
import com.alerdoci.ultimatepokedex.app.screens.home.composables.HomeScreen
import com.alerdoci.ultimatepokedex.app.screens.moves.composables.MovesScreen
import com.alerdoci.ultimatepokedex.app.screens.pokedex.composables.PokedexScreen
import com.alerdoci.ultimatepokedex.app.screens.pokemon.PokemonActivity
import com.alerdoci.ultimatepokedex.app.screens.pokemontypes.composables.TypesScreen
import com.alerdoci.ultimatepokedex.app.screens.welcome.composables.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Welcome
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        // Home
        composable(route = Screen.Home.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                HomeScreen(navController = navController)
            }
        }

        //Pokedex
        composable(route = Screen.Pokedex.route) {
            StartAnimation(
                enterTransition = slideInHorizontally() + fadeIn(),
                exitTransition = slideOutHorizontally() + fadeOut()
            ) {
                val appContext = LocalContext.current
                PokedexScreen(onItemClick = {pokemonClickedName ->
                    appContext.startActivity(
                   Intent(appContext, PokemonActivity::class.java).apply {
                       putExtra("pokemonName", pokemonClickedName)
                    }
                )
                })
            }
        }

        //Favorites
        composable(route = Screen.Favorites.route) {
            StartAnimation(
                enterTransition = scaleIn() + fadeIn(),
                exitTransition = scaleOut() + fadeOut()
            ) {
                FavoritesScreen()
            }
        }

        //Types
        composable(route = Screen.Types.route) {
            StartAnimation(
                enterTransition = scaleIn() + fadeIn(),
                exitTransition = scaleOut() + fadeOut()
            ) {
                TypesScreen()
            }
        }

        //Moves
        composable(route = Screen.Moves.route) {
            StartAnimation(
                enterTransition = scaleIn() + fadeIn(),
                exitTransition = scaleOut() + fadeOut()
            ) {
                MovesScreen()
            }
        }

//        //Imagine that I create the pokemon detail with Jetpack Compose
//        composable(route = Screen.Pokemon.route + "/{name}",
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            StartAnimation(
//                enterTransition = scaleIn() + fadeIn(),
//                exitTransition = scaleOut() + fadeOut()
//            ) {
//                PokemonDetailScreen()
//            }
//        }
    }
}