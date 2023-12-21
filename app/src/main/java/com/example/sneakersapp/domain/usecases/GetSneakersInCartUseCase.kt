package com.example.sneakersapp.domain.usecases

import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSneakersInCartUseCase @Inject constructor(
    private val sneakerRepository: Repository
) {
    suspend operator fun invoke() : Flow<List<SneakerCart>> {
        return sneakerRepository.getSneakersInCart()
    }
}