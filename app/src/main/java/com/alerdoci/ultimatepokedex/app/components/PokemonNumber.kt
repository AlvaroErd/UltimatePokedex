package com.alerdoci.ultimatepokedex.app.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alerdoci.ultimatepokedex.app.theme.dimens
import com.alerdoci.ultimatepokedex.app.theme.poke_black
import com.alerdoci.ultimatepokedex.app.theme.pokemonFont

@Composable
fun PokemonNumber(number: String) {
    Text(
        text = number,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontFamily = pokemonFont,
            fontSize = MaterialTheme.dimens.textSmallerBody,
            color = poke_black,
            letterSpacing = 2.sp,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview("Light Theme", showBackground = true)
@Composable
fun PokemonNumberPreview() {
    PokemonNumber(number = "127")
}