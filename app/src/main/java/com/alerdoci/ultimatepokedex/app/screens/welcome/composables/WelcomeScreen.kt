package com.alerdoci.ultimatepokedex.app.screens.welcome.composables

import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.app.navigation.Screen
import com.alerdoci.ultimatepokedex.app.screens.welcome.utils.LoaderIntro
import com.alerdoci.ultimatepokedex.app.screens.welcome.utils.OnBoardingData
import com.alerdoci.ultimatepokedex.app.screens.welcome.viewmodel.WelcomeViewModel
import com.alerdoci.ultimatepokedex.app.theme.BottomCardShape
import com.alerdoci.ultimatepokedex.app.theme.colorBlue
import com.alerdoci.ultimatepokedex.app.theme.colorGreen
import com.alerdoci.ultimatepokedex.app.theme.colorYellow
import com.alerdoci.ultimatepokedex.app.theme.dosisFont
import com.alerdoci.ultimatepokedex.app.theme.pokemonFont
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        val items = ArrayList<OnBoardingData>()

        items.add(
            OnBoardingData(
                R.raw.lottie_hello_squirtle,
                "Hello pokemon lover!",
                "Welcome to my application about the Pokemon world! I hope you enjoy it",
                backgroundColor = Color(0xFF87C4E3),
                mainColor = colorBlue
            )
        )
        items.add(
            OnBoardingData(
                R.raw.lottie_boy_developer_laptop,
                "I'm Alerdoci \n Mobile developer based in Spain",
                "This application has the purpose of demonstrating my knowledge about the Android world",
                backgroundColor = Color(0xFFFFDF3C),
                mainColor = colorYellow
            )
        )
        items.add(
            OnBoardingData(
                R.raw.lottie_android_jetpack,
                "Jetpack Compose POWER!",
                "Developed with Kotlin, J.C., Hilt, Retrofit, Material 3 and much more. Let's the show begin!",
                backgroundColor = Color(0xD30D2F41),
                mainColor = colorGreen
            )
        )
        val pagerState = rememberPagerState(
            initialPage = 0,
        )
        OnBoardingPager(
            item = items,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray),
            navController = navController,
            welcomeViewModel = hiltViewModel()
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@DelicateCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    Box(modifier = modifier) {
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(
                    BottomCenter
                )
            ) {
                HorizontalPager(state = pagerState, count = 3) {
                    val pageBackground = item[pagerState.currentPage].backgroundColor

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(pageBackground),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(340.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            ),
                            shape = BottomCardShape.large
                        ) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    PagerIndicator(
                                        items = item,
                                        currentPage = pagerState.currentPage
                                    )

                                    Text(
                                        text = item[pagerState.currentPage].title,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 15.dp)
                                            .padding(15.dp),
                                        color = item[pagerState.currentPage].mainColor,
                                        fontFamily = dosisFont,
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                    Text(
                                        text = item[pagerState.currentPage].desc,
                                        modifier = Modifier.padding(
                                            top = 20.dp,
                                            start = 40.dp,
                                            end = 40.dp
                                        ),
                                        color = Color.Gray,
                                        fontFamily = dosisFont,
                                        fontSize = 17.sp,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .align(BottomCenter)
                                        .padding(30.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        if (pagerState.currentPage != 2) {
                                            TextButton(onClick = {
                                                welcomeViewModel.saveOnBoardingState(completed = true)
                                                navController.popBackStack()
                                                navController.navigate(Screen.Home.route)
                                            }) {
                                                Text(
                                                    text = "Skip Now",
                                                    color = Color(0xFF292D32),
                                                    fontFamily = dosisFont,
                                                    textAlign = TextAlign.Right,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                            OutlinedButton(
                                                onClick = {
                                                    GlobalScope.launch {
                                                        withContext(Dispatchers.Main) {
                                                            pagerState.scrollToPage(
                                                                pagerState.currentPage + 1,
                                                                pageOffset = 0f
                                                            )
                                                        }
                                                    }
                                                },
                                                border = BorderStroke(
                                                    14.dp,
                                                    item[pagerState.currentPage].mainColor
                                                ),
                                                shape = CircleShape,
                                                colors = ButtonDefaults.outlinedButtonColors(
                                                    contentColor = item[pagerState.currentPage].mainColor
                                                ),
                                                modifier = Modifier.size(65.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_right_arrow),
                                                    contentDescription = "",
                                                    tint = item[pagerState.currentPage].mainColor,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        } else {
                                            FinishButton(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(2.dp),
                                                pagerState = pagerState,
                                            ) {
                                                welcomeViewModel.saveOnBoardingState(completed = true)
                                                navController.popBackStack()
                                                navController.navigate(Screen.Home.route)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(TopCenter),
            ) {
                HorizontalPager(state = pagerState, count = 3) { page2 ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        LoaderIntro(
                            modifier =
                            (if (pagerState.currentPage == 1) {
                                Modifier
                                    .size(340.dp)
                                    .fillMaxWidth()
                                    .align(alignment = Alignment.CenterHorizontally)
                            } else {
                                Modifier
                                    .size(340.dp)
                                    .fillMaxWidth()
                                    .padding(end = 20.dp, top = 30.dp)
                                    .align(alignment = Alignment.CenterHorizontally)
                            }),
                            item[page2].image
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(
                isSelected = it == currentPage,
                color = items[it].mainColor,
                selectedImage = R.drawable.ic_pokeball_onboarding,
                unselectedImage = R.drawable.ic_pokeball_onboarding
            )
        }
    }
}


@Composable
fun Indicator(
    isSelected: Boolean,
    color: Color,
    selectedImage: Int,
    unselectedImage: Int
) {
    val size = animateDpAsState(targetValue = if (isSelected) 20.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(20.dp)
            .size(size.value),
        contentAlignment = Center
    ) {
        Image(
            painter = painterResource(if (isSelected) selectedImage else unselectedImage),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = if (isSelected) ColorFilter.tint(color) else ColorFilter.tint(
                Color.Gray
            )
        )
    }
}


@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @IntRange(from = 0) initialPage: Int = 0
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        currentPage = initialPage,
    )
}

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
                    fontSize = 16.sp,
                    fontFamily = pokemonFont,
                    color = Color(0xFFF8B24A),
//                    style = TextStyle(
//                        shadow = Shadow(
//                            color = Color.LightGray,
//                            offset = offset,
//                            blurRadius = 20f,),)
                )
            }
        }
    }
}