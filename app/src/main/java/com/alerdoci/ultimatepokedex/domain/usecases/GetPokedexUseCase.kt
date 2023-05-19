package com.alerdoci.ultimatepokedex.domain.usecases

import com.alerdoci.ultimatepokedex.app.common.utils.Constants.LIMIT
import com.alerdoci.ultimatepokedex.domain.models.features.pokedex.ModelListPokedex
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(
    private val repository: PokedexRepository
) {

    suspend operator fun invoke(): Flow<List<ModelListPokedex>> =
        repository.getPokedex(limit = LIMIT, offset = 0)

}