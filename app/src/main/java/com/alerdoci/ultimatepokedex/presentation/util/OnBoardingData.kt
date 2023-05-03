package com.alerdoci.ultimatepokedex.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

data class OnBoardingData(val image: Int, val title: String, val desc: String)

@Composable
fun LoaderIntro(modifier: Modifier, image: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

//sealed class OnBoardingData(
//    val animationResId: Int,
//    val title: String,
//    val description: String
//) {
//    object Item1 : OnBoardingData(
//        R.raw.lottie_hello_squirtle,
//        "Animation 1",
//        "This is the first animation"
//    )
//
//    object Item2 : OnBoardingData(
//        R.raw.lottie_loading_gameboy,
//        "Animation 2",
//        "This is the second animation"
//    )
//
//    object Item3 : OnBoardingData(
//        R.raw.lottie_nintendo_switch,
//        "Animation 3",
//        "This is the third animation"
//    )
//}
