package com.alerdoci.ultimatepokedex.presentation.screen

import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.navigation.Screen
import com.alerdoci.ultimatepokedex.presentation.theme.BottomCardShape
import com.alerdoci.ultimatepokedex.presentation.theme.ColorBlue
import com.alerdoci.ultimatepokedex.presentation.theme.ColorYellow
import com.alerdoci.ultimatepokedex.presentation.theme.dosisFont
import com.alerdoci.ultimatepokedex.presentation.theme.md_theme_light_primaryContainer
import com.alerdoci.ultimatepokedex.presentation.theme.pokemonFont
import com.alerdoci.ultimatepokedex.presentation.util.LoaderIntro
import com.alerdoci.ultimatepokedex.presentation.util.OnBoardingData
import com.alerdoci.ultimatepokedex.presentation.viewmodel.WelcomeViewModel
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
                "I´m Alerdoci, I hope you like the app!Cu praesent sapientem falli definiebas malorum constituam delicata. ",
                backgroundColor = Color(0xFF87C4E3),
                mainColor = ColorBlue
            )
        )
        items.add(
            OnBoardingData(
                R.raw.lottie_boy_developer_laptop,
                "Taste a different dish every day!",
                "Eat the food you want with a wide range of products!",
                backgroundColor = Color(0xFFFFDF3C),
                mainColor = ColorYellow
            )
        )
        items.add(
            OnBoardingData(
                R.raw.lottie_android_jetpack,
                "You have your order in minutes!",
                "Easy ordering and fast transportation",
                backgroundColor = Color(0xD30D2F41),
                mainColor = Color(0xB9073042)
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState, count = 3) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    LoaderIntro(
                        modifier = Modifier
                            .size(300.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                        item[page].image
                    )
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
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
                                end = 20.dp
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
                            .align(Alignment.BottomCenter)
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

@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage, color = items[it].mainColor)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
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
                    fontFamily = pokemonFont,
                    color = md_theme_light_primaryContainer
                )
            }
        }
    }
}