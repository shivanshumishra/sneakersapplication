package com.example.sneakersapp.domain.repository

import com.example.sneakersapp.data.local.dto.SneakerDto
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.model.SneakerCart
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getSneakers() : List<SneakerDto>

    suspend fun getSneakerById(id : Int) : SneakerDto?

    suspend fun getSneakersInCart() : Flow<List<SneakerCart>>

    suspend fun insertSneakerInCart(sneaker: SneakerCart)

    suspend fun deleteSneakerFromCart(sneaker: SneakerCart)

}