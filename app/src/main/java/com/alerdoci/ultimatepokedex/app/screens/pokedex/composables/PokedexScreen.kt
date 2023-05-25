package com.alerdoci.ultimatepokedex.app.screens.pokedex.composables

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.app.common.extensions.Extensions.capitalized
import com.alerdoci.ultimatepokedex.app.common.states.ResourceState
import com.alerdoci.ultimatepokedex.app.components.ImageGif
import com.alerdoci.ultimatepokedex.app.components.PokemonName
import com.alerdoci.ultimatepokedex.app.components.PokemonNumber
import com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel.PokedexViewModel
import com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel.pokemonMock1
import com.alerdoci.ultimatepokedex.app.theme.TopCardShape
import com.alerdoci.ultimatepokedex.app.theme.dosisFont
import com.alerdoci.ultimatepokedex.app.theme.poke_light_red
import com.alerdoci.ultimatepokedex.app.theme.poke_red_dark
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelPokedexList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokedexScreen(onItemClick: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        var textSearched by remember { mutableStateOf("") }
        var textActive by remember { mutableStateOf(false) }

        Scaffold() {
            Column() {
                Card(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    colors = CardDefaults.cardColors(containerColor = poke_red_dark),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = TopCardShape.large
                )
                {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = CenterHorizontally,
                    ) {
                        ImageGif(
                            img = R.drawable.pokemon_logo_animated_shimme,
                            imgGifModifier = Modifier.height(60.dp)
                        )
                        SearchBar(
                            query = textSearched,
                            onQueryChange = { newTextSerached ->
                                textSearched =
                                    newTextSerached
                            },
                            onSearch = {
                                textActive = false
//                                textSearched = ""
                            },
                            active = textActive,
                            onActiveChange = {
                                textActive = it
                            },
                            placeholder = { Text(text = "Search pokemon (Coming soon)") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search icon"
                                )
                            },
                            colors = SearchBarDefaults.colors(dividerColor = poke_light_red),
                            trailingIcon = {
                                if (textActive) {
                                    Icon(
                                        modifier = Modifier.clickable {
                                            if (textSearched.isNotEmpty()) {
                                                textSearched = ""
                                            } else {
                                                textActive = false
                                            }
                                        },
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close icon",
                                    )
                                }
                            },
                        ) {

                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally,
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        PokedexList(onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun PokedexList(
    viewModel: PokedexViewModel = hiltViewModel(),
    onItemClick: (pokemonName: String) -> Unit,
) {
    val pokedexListState by viewModel.pokedex.collectAsStateWithLifecycle()
    var items by remember { mutableStateOf<List<ModelPokedexList>>(emptyList()) }

    when (pokedexListState) {
        is ResourceState.Loading -> Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val animation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_three_digglets_loading))
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                LottieAnimation(
                    composition = animation,
                    isPlaying = true,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .height(140.dp)
                        .padding(bottom = 10.dp)
                )
            }
        }

        is ResourceState.Error -> Box(
            contentAlignment = Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column() {
                Text(
                    text = stringResource(R.string.oops_something_went_wrong),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 40.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = dosisFont,

                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFEC5262), Color(0xFFDAC8C8), Color(0xFFEC5262))
                        )
                    ),
                )
                ImageGif(img = R.drawable.pikachu_sad, imgGifModifier = Modifier.size(160.dp))
            }
        }

        is ResourceState.Success -> items =
            (pokedexListState as ResourceState.Success).data as List<ModelPokedexList>

        else -> {}
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
        contentPadding = PaddingValues(start = 4.dp, end = 4.dp, bottom = 4.dp, top = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        items(items) { pokemonItem ->
            println("Pokemon name (his ID): ${pokemonItem.name}")
            PokemonCard(pokemon = pokemonItem, onItemClick = onItemClick)
        }
    }
}


@Composable
fun PokemonCard(
    pokemon: ModelPokedexList,
    modifier: Modifier = Modifier,
    viewModel: PokedexViewModel = hiltViewModel(),
    onItemClick: (pokemonId: String) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }

    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(dominantColor)
            .clickable {
                onItemClick(pokemon.name)
            },
    ) {
        Column {
            val imageLoader = ImageLoader(LocalContext.current)
            val req = ImageRequest.Builder(LocalContext.current).data(pokemon.imageUrl)
                .crossfade(true)
                .build()
            LaunchedEffect(key1 = "fetchColors") {
                val drawable = imageLoader.execute(req).drawable
                drawable?.let {
                    viewModel.calculateDominantColor(it) { color ->
                        dominantColor = color
                    }
                }
            }
            val painter = rememberAsyncImagePainter(model = req)
            val painterState = painter.state
            Image(
                painter = painter,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            if (painterState is AsyncImagePainter.State.Loading) {
                Box(contentAlignment = Center) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_question_mark_pokemon),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            PokemonNumber(number = pokemon.pokemonNumber)
            PokemonName(text = pokemon.name.capitalized())
        }
    }
}


@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(pokemon = pokemonMock1, onItemClick = {})
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PokedexScreenPreview() {
    PokedexScreen(onItemClick = {})
}