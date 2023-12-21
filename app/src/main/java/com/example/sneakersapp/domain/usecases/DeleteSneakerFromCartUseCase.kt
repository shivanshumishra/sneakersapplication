package com.example.sneakersapp.domain.usecases

import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.repository.Repository
import javax.inject.Inject

class DeleteSneakerFromCartUseCase @Inject constructor(
    private val sneakerRepository: Repository
) {
    suspend operator fun invoke(sneaker: SneakerCart) {
        sneakerRepository.deleteSneakerFromCart(sneaker)
    }
}