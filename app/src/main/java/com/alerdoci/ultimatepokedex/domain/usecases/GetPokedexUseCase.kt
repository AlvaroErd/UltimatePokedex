package com.alerdoci.ultimatepokedex.domain.usecases

import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(
    private val pokedexDataStore: PokedexRepository
) {

    suspend operator fun invoke(): Flow<List<ModelListPokedex>> =
        pokedexDataStore.getPokedex(limit = 151, offset = 0)

}