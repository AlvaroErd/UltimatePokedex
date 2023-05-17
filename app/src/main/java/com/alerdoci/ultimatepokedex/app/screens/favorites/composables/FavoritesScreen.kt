package com.alerdoci.ultimatepokedex.app.screens.favorites.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.app.theme.dosisFont

@OptIn(ExperimentalTextApi::class)
@Composable
fun FavoritesScreen() {
    val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loading_gameboy))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(0.dp, (-80).dp),
        ) {
            LottieAnimation(
                composition = animation,
                isPlaying = true,
                restartOnPlay = true,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxSize(fraction = 0.4f)
                    .offset(0.dp, 45.dp)
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.coming_soon),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 40.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = dosisFont,
                    brush = Brush.linearGradient(
                        // horizontalGradient , linearGradient , verticalGradient , sweepGradient , radialGradient
                        colors = listOf(Color(0xFF7851CD), Color(0xFF46C7E4), Color(0xFF7851CD))
                    )
                ),
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = stringResource(R.string.favorites_is_not_implemented),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                fontFamily = dosisFont,
            )
        }
    }
}


@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}