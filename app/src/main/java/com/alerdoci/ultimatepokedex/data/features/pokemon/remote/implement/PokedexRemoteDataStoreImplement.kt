package com.alerdoci.ultimatepokedex.data.features.pokedex.remote.implement

//import com.alerdoci.ultimatepokedex.data.remote.service.PokedexService
//import com.hiberus.cursopreparacionmango.data.datasource.features.animals.mappers.toDomain
//import com.hiberus.cursopreparacionmango.data.datasource.remote.service.PetFinderService
//import com.hiberus.cursopreparacionmango.data.datastore.features.animals.AnimalDataStore
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.Animal
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.AnimalType
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.AnimalTypeBreed
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import java.util.Date
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//open class PokedexRemoteDataStoreImplement @Inject constructor(
//    private val remoteService: PokedexService,
//) : PokemonRepository {
//
//    override suspend fun getPokedex(limit: Int, offset: Int): Resource<List<Pokemon>> {
//        return httpRequest {
//            val pokemons = remoteService.getPokemons(limit, offset)
//            pokemons.results.map {
//                remoteService.getPokemonByName(it.name).toPokemon()
//            }
//        }
//    }