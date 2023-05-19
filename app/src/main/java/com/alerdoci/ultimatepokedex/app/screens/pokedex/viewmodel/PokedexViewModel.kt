package com.alerdoci.ultimatepokedex.app.screens.pokedex.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.alerdoci.ultimatepokedex.app.common.states.ResourceState
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import com.alerdoci.ultimatepokedex.domain.usecases.GetPokedexUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokedexUseCase: GetPokedexUseCase
) : ViewModel() {

    private val _pokedex by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val pokedex: StateFlow<ResourceState<*>>
        get() = _pokedex

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokedex()
        }
    }

    private suspend fun loadPokedex() {
        if (_pokedex.compareAndSet(ResourceState.Idle, ResourceState.Loading(""))) {
            getPokedexUseCase()
                .onCompletion { throwable ->
                    if (throwable == null)
                        _pokedex.update { ResourceState.Idle }
                    else
                        _pokedex.update { ResourceState.Error(throwable) }
                }
                .collectLatest {
                    _pokedex.update { ResourceState.Success(pokedex) }
                }
        }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}

//Mock
val pokemonMock1 = ModelListPokedex(
    name = "Bolbasor",
    url = "",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    pokemonNumber = "1"
)
val pokemonMock2 = ModelListPokedex(
    name = "Ivisor",
    url = "",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png",
    pokemonNumber = "2"
)
val pokemonMock3 = ModelListPokedex(
    name = "Venosor",
    url = "",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png",
    pokemonNumber = "3"
)

var pokedexMock = listOf(
    pokemonMock1,
    pokemonMock2,
    pokemonMock3,
)
