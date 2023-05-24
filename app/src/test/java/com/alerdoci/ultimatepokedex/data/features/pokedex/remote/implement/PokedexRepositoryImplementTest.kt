package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.implement

import com.alerdoci.ultimatepokedex.data.remote.service.PokedexService
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

internal class PokedexRepositoryImplementTest {
    private val pokedexService = mockk<PokedexService>()
    private lateinit var pokedexRepository: PokedexRepository

    companion object {
        private val responseMock = Any()
        private val resultExpected = Any()
    }

    @Before
    fun setUp() {
        pokedexRepository = PokedexRepositoryImplement(pokedexService)
    }

    @Test
    fun `Api service returns error`() = runBlocking {
        coEvery { pokedexService.getPokedex(any(), any()) } returns Response.error(
            400,
            "".toResponseBody(null)
        )
        val result = pokedexRepository.getPokedex(0, 0).toList()
        assertTrue(result.first().isEmpty())
    }

}