package com.example.sneakersapp.domain.usecases

import com.example.sneakersapp.common.Resource
import com.example.sneakersapp.data.local.dto.toSneaker
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSneakersUseCase @Inject constructor(
    private val sneakerRepository: Repository
) {
    operator fun invoke() : Flow<Resource<List<Sneaker>>>  = flow {
        try {
            emit(Resource.Loading<List<Sneaker>>())
            delay(2000) //just to show loader
            val sneaker  = sneakerRepository.getSneakers()
            emit(Resource.Success<List<Sneaker>>(sneaker.map { it.toSneaker() }))
        } catch (e : IOException){
            emit(Resource.Error<List<Sneaker>>("Couldn't Reach Server, Check your internet"))
        } catch (e: Exception){
            emit(Resource.Error<List<Sneaker>>(e.localizedMessage ?: "Unexpected Error Occurred"))
        }
    }
}