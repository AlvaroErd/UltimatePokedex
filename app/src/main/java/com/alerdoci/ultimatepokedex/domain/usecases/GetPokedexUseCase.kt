package com.alerdoci.ultimatepokedex.domain.usecases

import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelPokedexList
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(
    private val repository: PokedexRepository
) {

    suspend operator fun invoke(): Flow<List<ModelPokedexList>> =
        repository.getPokedex(limit = 151, offset = 0)

}