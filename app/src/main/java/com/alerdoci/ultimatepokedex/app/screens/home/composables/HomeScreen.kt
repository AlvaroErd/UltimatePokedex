package com.alerdoci.ultimatepokedex.app.screens.home.composables

import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.app.components.CardMain
import com.alerdoci.ultimatepokedex.app.navigation.Screen
import com.alerdoci.ultimatepokedex.app.theme.TopCardShape
import com.alerdoci.ultimatepokedex.app.theme.UltimatePokedexTheme
import com.alerdoci.ultimatepokedex.app.theme.dosisFont
import com.alerdoci.ultimatepokedex.app.theme.poke_light_blue
import com.alerdoci.ultimatepokedex.app.theme.poke_light_yellow
import com.alerdoci.ultimatepokedex.app.theme.poke_purple
import com.alerdoci.ultimatepokedex.app.theme.poke_red
import com.alerdoci.ultimatepokedex.app.theme.spacing


@Composable
fun HomeScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.pokemon_ost_title_screen) }
    var isPlaying by remember { mutableStateOf(false) }
    val lottiePlay by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_play_pause))
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing))
    )
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                colors = CardDefaults.cardColors(
                    containerColor = poke_red,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                shape = TopCardShape.large
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopEnd,
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_pokeball), contentDescription = "",
                            modifier = Modifier
                                .size(300.dp)
                                .alpha(0.1f)
                                .graphicsLayer {
                                    translationY = -200f
                                    translationX = 200f
                                },
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.what_are_you_looking_for),
                        fontSize = 34.sp,
                        lineHeight = 30.sp,
                        fontFamily = dosisFont,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp),
                        color = Color.White
                    )
                }
            }
            Column(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.extraMedium,
                    end = MaterialTheme.spacing.extraMedium,
                    top = MaterialTheme.spacing.extraMedium,
                    bottom = MaterialTheme.spacing.small
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CardMain(
                    text = stringResource(R.string.pokedex),
                    img = R.drawable.ic_pokeball,
                    onClick = { navController.navigate(Screen.Pokedex.route) },
                    colorImgTint = ColorFilter.tint(Color.White),
                    backgroundColor = poke_red,
                    imgModifier = Modifier
                        .size(90.dp)
                        .graphicsLayer {
                            rotationZ = 28f
                            translationY = 40f
                            translationX = 40f
                        }
                )
                CardMain(
                    text = stringResource(R.string.favorites),
                    img = R.drawable.ic_pokeball,
                    onClick = { navController.navigate(Screen.Favorites.route) },
                    colorImgTint = ColorFilter.tint(Color.White),
                    backgroundColor = poke_light_yellow,
                    imgModifier = Modifier
                        .size(90.dp)
                        .graphicsLayer {
                            rotationZ = 28f
                            translationY = 40f
                            translationX = 40f
                        }
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CardMain(
                        text = stringResource(R.string.types),
                        modifier = Modifier.weight(0.5f),
                        backgroundColor = poke_purple,
                        img = R.drawable.ic_pokeball,
                        colorImgTint = ColorFilter.tint(Color.White),
                        onClick = {
                            navController.navigate(Screen.Types.route)
                        },
                        imgModifier = Modifier
                            .graphicsLayer {
                                rotationZ = 28f
                                translationY = 40f
                                translationX = 40f
                            }
                    )
                    CardMain(
                        text = stringResource(R.string.moves),
                        modifier = Modifier.weight(0.5f),
                        backgroundColor = poke_light_blue,
                        onClick = { navController.navigate(Screen.Moves.route) },
                        img = R.drawable.ic_pokeball,
                        colorImgTint = ColorFilter.tint(Color.White),
                        imgModifier = Modifier
                            .graphicsLayer {
                                rotationZ = 28f
                                translationY = 40f
                                translationX = 40f
                            }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 25.dp, start = 10.dp)
//                    .wrapContentSize(Alignment.BottomCenter)
                    .offset(0.dp, 30.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Button(
                        modifier = Modifier
                            .height(110.dp)
                            .width(110.dp)
                            .drawBehind {
                                rotate(rotationAnimation.value) {
                                    drawCircle(
                                        rainbowColorsBrush,
                                        radius = 30.dp.toPx(),
                                        style = Stroke(width = 6.dp.toPx())
                                    )
                                }
                            }
                            .padding(2.dp)
                            .background(Color.Transparent)
                            .clip(CircleShape),
                        onClick = {
                            isPlaying = !isPlaying
                            if (isPlaying) {
                                mediaPlayer.start()
                            } else {
                                mediaPlayer.pause()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        ),
                    )
                    {
                        if (isPlaying) {
                            LottieAnimation(
                                composition = lottiePlay,
                                clipSpec = LottieClipSpec.Progress(0.0f, 0.5f)
                            )
                        } else {
                            LottieAnimation(
                                composition = lottiePlay,
                                clipSpec = LottieClipSpec.Progress(0.5f, 1f)
                            )
                        }
                    }
                    Text(
                        text =
                        if (isPlaying) {
                            stringResource(id = R.string.now_playing)
                        } else {
                            stringResource(id = R.string.play_music)
                        },
                        fontSize = 16.sp,
                        fontFamily = dosisFont,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    UltimatePokedexTheme() {
        HomeScreen(navController = navController)
    }
}