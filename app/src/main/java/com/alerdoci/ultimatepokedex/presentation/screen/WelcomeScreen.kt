package com.alerdoci.ultimatepokedex.presentation.screen

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.navigation.Screen
import com.alerdoci.ultimatepokedex.presentation.theme.md_theme_dark_background
import com.alerdoci.ultimatepokedex.presentation.theme.md_theme_dark_error
import com.alerdoci.ultimatepokedex.presentation.theme.md_theme_light_primaryContainer
import com.alerdoci.ultimatepokedex.presentation.theme.pokemonFont
import com.alerdoci.ultimatepokedex.presentation.util.LoaderIntro
import com.alerdoci.ultimatepokedex.presentation.util.OnBoardingData
import com.alerdoci.ultimatepokedex.presentation.viewmodel.WelcomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlin.system.exitProcess


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val items = ArrayList<OnBoardingData>()
    items.add(
        OnBoardingData(
            R.raw.lottie_hello_squirtle,
            "Hello pokemon lover!",
            "I´m Alerdoci, I hope you like the app!"
        )
    )
    items.add(
        OnBoardingData(
            R.raw.lottie_nintendo_switch,
            "Taste a different dish every day!",
            "Eat the food you want with a wide range of products!"
        )
    )
    items.add(
        OnBoardingData(
            R.raw.lottie_boy_developer_laptop,
            "You have your order in minutes!",
            "Easy ordering and fast transportation"
        )
    )
    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    )
    FinishButton(
        modifier = Modifier.fillMaxWidth(),
        pagerState = pagerState
    ) {
        welcomeViewModel.saveOnBoardingState(completed = true)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(60.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoaderIntro(
                        modifier = Modifier
                            .size(300.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally), item[page].image
                    )
                    Text(
                        text = item[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            PagerIndicator(item.size, pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomSection(pagerState.currentPage, onSkipClick = {
                pagerState.pageCount + 2
            }, onNextClick = {
                if (pagerState.currentPage == item.size - 1) {
                    exitProcess(0)
                } else {
                    pagerState.pageCount + 1
                }
            }, isSkipClicked = true)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @IntRange(from = 0) pageCount: Int,
    @IntRange(from = 0) initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    @IntRange(from = 1) initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(
    saver = PagerState.Saver
) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}

@Composable
fun PagerIndicator(
    size: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 60.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) md_theme_dark_background else md_theme_dark_error.copy(alpha = 0.5f)
            )
    )
}

@ExperimentalPagerApi
@Composable
fun BottomSection(
    currentPage: Int,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    isSkipClicked: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
            .fillMaxWidth()
    ) {
        if (currentPage != 2) {
            if (isSkipClicked) {
                androidx.compose.material3.Text(
                    text = "Skip",
                    color = md_theme_dark_background,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clickable {
                            onSkipClick()
                        }
                )
            }
        }
        if (currentPage == 2) {
            androidx.compose.material3.Text(
                text = "Done",
                color = md_theme_dark_background,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clickable(onClick = {
                        exitProcess(0)
                    })
            )
        } else {
            androidx.compose.material3.Text(
                text = "Next",
                color = md_theme_dark_background,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .clickable(onClick = {
                        onNextClick()
                    })
            )
        }
    }
}

//

//
//@ExperimentalAnimationApi
//@ExperimentalPagerApi
//@Composable
//fun WelcomeScreen(
//    navController: NavHostController,
//    welcomeViewModel: WelcomeViewModel = hiltViewModel()
//) {
//    val pages = listOf(
//        OnBoardingData.Item1,
//        OnBoardingData.Item2,
//        OnBoardingData.Item3
//    )
//    val pagerState = rememberPagerState(3)
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        HorizontalPager(
//            modifier = Modifier.weight(10f),
//            state = pagerState,
//            verticalAlignment = Alignment.Top
//        ) { position ->
//            PagerScreen(onBoardingPage = pages[position])
//        }
//        HorizontalPagerIndicator(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .weight(1f),
//            pagerState = pagerState
//        )
//        FinishButton(
//            modifier = Modifier.weight(1f),
//            pagerState = pagerState
//        ) {
//            welcomeViewModel.saveOnBoardingState(completed = true)
//            navController.popBackStack()
//            navController.navigate(Screen.Home.route)
//        }
//    }
//}
//
//@Composable
//fun PagerScreen(onBoardingPage: OnBoardingData) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Image(
//            modifier = Modifier
//                .fillMaxWidth(0.5f)
//                .fillMaxHeight(0.7f),
//            painter = painterResource(onBoardingPage.animationResId),
//            contentDescription = "Pager Image"
//        )
//        Text(
//            modifier = Modifier
//                .fillMaxWidth(),
//            text = onBoardingPage.title,
//            fontSize = MaterialTheme.typography.titleLarge.fontSize,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 40.dp)
//                .padding(top = 20.dp),
//            text = onBoardingPage.description,
//            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
//            fontWeight = FontWeight.Medium,
//            textAlign = TextAlign.Center
//        )
//    }
//}
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            OutlinedButton(
                modifier = modifier
                    .padding(16.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = onClick,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                ),
                elevation = ButtonDefaults.buttonElevation(),
//                border = ButtonDefaults.outlinedButtonBorder,
                border = BorderStroke(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDA9A4E),
                            Color(0xFF3B02E5)
                        ),
                    ),
                )
            ) {
                Text(
                    text = "Let's Go!",
                    fontFamily = pokemonFont,
                    color = md_theme_light_primaryContainer
                )
            }
        }
    }
}

//@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
//@Preview
//@Composable
//fun FinishButtonPreview() {
//    FinishButton(modifier = Modifier.fillMaxWidth(), pagerState = PagerState(2)) {
//    }
//}

//
//@Composable
//@Preview(showBackground = true)
//fun FirstOnBoardingScreenPreview() {
//    Column(modifier = Modifier.fillMaxSize()) {
//        PagerScreen(onBoardingPage = OnBoardingPage.First)
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun SecondOnBoardingScreenPreview() {
//    Column(modifier = Modifier.fillMaxSize()) {
//        PagerScreen(onBoardingPage = OnBoardingPage.Second)
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun ThirdOnBoardingScreenPreview() {
//    Column(modifier = Modifier.fillMaxSize()) {
//        PagerScreen(onBoardingPage = OnBoardingPage.Third)
//    }
//}

