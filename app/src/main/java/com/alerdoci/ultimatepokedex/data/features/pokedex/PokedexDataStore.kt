package com.alerdoci.ultimatepokedex.data.features.pokedex
//
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.Animal
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.AnimalTypeBreed
//import com.hiberus.cursopreparacionmango.domain.models.features.animals.AnimalType
//import kotlinx.coroutines.flow.Flow
//import java.util.Date
//
//interface PokedexDataStore {
//    fun getAnimalTypes(): Flow<List<AnimalType>>
//    suspend fun insertOrUpdateAnimalTypes(vararg animalType: AnimalType)
//    fun getAnimalBreedsByType(animalType: AnimalType): Flow<List<AnimalTypeBreed>>
//    suspend fun insertOrUpdateAnimalTypeBreed(vararg  animalTypeBreed: AnimalTypeBreed)
//    suspend fun insertOrUpdateAnimals(vararg animal: Animal)
//
//    fun getAnimalById(animalId: Long): Flow<Animal>
//
//    fun getFilteredAnimals(
//        page: Int = 1,
//        limit: Int = 20,
//        type: String? = null,
//        breed: String? = null,
//        size: String? = null,
//        gender: String? = null,
//        age: String? = null,
//        color: String? = null,
//        coat: String? = null,
//        status: String? = null,
//        name: String? = null,
//        goodWithChildren: Boolean? = null,
//        goodWithDogs: Boolean? = null,
//        goodWithCats: Boolean? = null,
//        houseTrained: Boolean? = null,
//        declawed: Boolean? = null,
//        specialNeeds: Boolean? = null,
//        location: String? = null,
//        distance: Int? = null,
//        before: Date? = null,
//        after: Date? = null,
//        sort: String? = null,
//    ): Flow<List<Animal>>
//}