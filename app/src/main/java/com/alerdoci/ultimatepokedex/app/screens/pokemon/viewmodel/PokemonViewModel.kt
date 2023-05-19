package com.alerdoci.ultimatepokedex.app.screens.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alerdoci.ultimatepokedex.app.common.states.ResourceState
import com.alerdoci.ultimatepokedex.domain.usecases.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InvalidObjectException
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(

    private val getPokemonUseCase: GetPokemonUseCase

) : ViewModel() {

    private val _currentPokemon by lazy { MutableStateFlow<ResourceState<*>>(ResourceState.Idle) }
    val currentPokemon: StateFlow<ResourceState<*>>
        get() = _currentPokemon


    fun loadPokemon(pokemonName: String) {
        _currentPokemon.update { ResourceState.Loading("") }
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonUseCase(pokemonName = pokemonName).collectLatest { pokemon ->
                _currentPokemon.update {
                    if (pokemon.name != "")
                        ResourceState.Success(pokemon)
                    else
                        ResourceState.Error(InvalidObjectException("Pokemon not found :("))
                }
            }
        }
    }
}
