package com.alerdoci.ultimatepokedex.app.screens.pokedex.composables

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.alerdoci.ultimatepokedex.R
import com.alerdoci.ultimatepokedex.app.common.extensions.Extensions.capitalized
import com.alerdoci.ultimatepokedex.app.components.ImageGif
import com.alerdoci.ultimatepokedex.app.components.PokemonName
import com.alerdoci.ultimatepokedex.app.components.PokemonNumber
import com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel.PokedexViewModel
import com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel.pokedexMock
import com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel.pokemonMock1
import com.alerdoci.ultimatepokedex.app.theme.TopCardShape
import com.alerdoci.ultimatepokedex.app.theme.blue_grey_800
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex

@Composable
fun PokedexScreen(viewModel: PokedexViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        val pokedexListData by viewModel.pokedexFlow.collectAsState()

        Column() {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = CardDefaults.cardColors(containerColor = blue_grey_800),
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
                        imgGifModifier = Modifier.size(160.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    PokedexList(pokedexListData, onItemClick = {})
                }
            }
        }
    }
}


@Composable
fun PokedexList(
    pokedexList: List<ModelListPokedex>,
    onItemClick: (pokemonId: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentPadding = PaddingValues(all = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        items(pokedexList) { item ->
            PokemonCard(item) {
                onItemClick(item.pokemonId)
            }
        }
    }
}

@Composable
fun PokemonCard(
    item: ModelListPokedex,
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
                onItemClick(item.pokemonId)
            },
    ) {
        Column {
            val imageLoader = ImageLoader(LocalContext.current)
            val req = ImageRequest.Builder(LocalContext.current).data(item.imageUrl).crossfade(true)
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
                contentDescription = item.name,
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
            PokemonNumber(number = item.pokemonId)
            PokemonName(text = item.name.capitalized())
        }
    }
}


@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BoxPokemonPreview() {
    PokedexList(pokedexList = pokedexMock, onItemClick = {})
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(item = pokemonMock1, onItemClick = {})
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PokedexScreenPreview() {
    PokedexScreen()
}