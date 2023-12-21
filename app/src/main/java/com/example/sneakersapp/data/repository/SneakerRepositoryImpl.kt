package com.example.sneakersapp.data.repository

import android.content.Context
import com.example.sneakersapp.data.data_source.SneakersDao
import com.example.sneakersapp.data.local.dto.SneakerDto
import com.example.sneakersapp.domain.model.Sneaker
import com.example.sneakersapp.domain.model.SneakerCart
import com.example.sneakersapp.domain.repository.Repository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import java.io.BufferedReader
import java.io.FileReader


class SneakerRepositoryImpl(
    private val context: Context,
    private val dao: SneakersDao) : Repository {
    override suspend fun getSneakers(): List<SneakerDto> {
        val jsonString = context.assets.open("sneakers/Sneakers.json")
            .bufferedReader()
            .use { it.readText() }
        val listSneakers = object : TypeToken<List<SneakerDto>>() {}.type
        return Gson().fromJson(jsonString, listSneakers)
    }

    override suspend fun getSneakerById(id: Int): SneakerDto? {
        val jsonString = context.assets.open("sneakers/Sneakers.json")
            .bufferedReader()
            .use { it.readText() }
        val listSneakers = object : TypeToken<List<SneakerDto>>() {}.type
        val list : List<SneakerDto> = Gson().fromJson(jsonString, listSneakers)
        list.forEach {
            if(it.id == id){
                return it
            }
        }
        return null
    }

    override suspend fun getSneakersInCart(): Flow<List<SneakerCart>> {
        return dao.getSneakersInCart()
    }

    override suspend fun insertSneakerInCart(sneaker: SneakerCart) {
        return dao.insertOrUpdate(sneaker)
    }

    override suspend fun deleteSneakerFromCart(sneaker: SneakerCart) {
        dao.deleteSneakerFromCart(sneaker)
    }
}