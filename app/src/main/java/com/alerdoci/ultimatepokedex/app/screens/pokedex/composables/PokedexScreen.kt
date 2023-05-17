package com.alerdoci.ultimatepokedex.app.screens.pokedex.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.alerdoci.ultimatepokedex.R

@Preview
@Composable
fun PokedexScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/132.png",
            contentDescription = "",
            placeholder = painterResource(R.drawable.ic_question_mark_pokemon)
        )
    }
}