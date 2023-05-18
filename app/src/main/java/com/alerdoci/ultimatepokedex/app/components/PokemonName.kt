package com.alerdoci.ultimatepokedex.app.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alerdoci.ultimatepokedex.app.theme.dosisFont
import com.alerdoci.ultimatepokedex.app.theme.poke_black

@Composable
fun PokemonName(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontFamily = dosisFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = poke_black
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PokemonNamePreview() {
    PokemonName(text = "Psyaaaaydack")
}