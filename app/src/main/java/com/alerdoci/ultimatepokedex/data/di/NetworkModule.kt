package com.alerdoci.ultimatepokedex.data.di

import com.alerdoci.ultimatepokedex.data.datasources.remote.RemoteConstants.Companion.BASE_URL
import com.alerdoci.ultimatepokedex.data.features.pokedex.remote.implement.PokedexRepositoryImplement
import com.alerdoci.ultimatepokedex.data.remote.service.PokedexService
import com.alerdoci.ultimatepokedex.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): PokedexService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build().create(PokedexService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(remoteService: PokedexService): PokedexRepository {
        return PokedexRepositoryImplement(remoteService)
    }

}

