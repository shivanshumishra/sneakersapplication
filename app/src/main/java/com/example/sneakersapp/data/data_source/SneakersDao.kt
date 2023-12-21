package com.example.sneakersapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.domain.model.SneakerCart
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakersDao {

    @Query("SELECT * from sneakercart")
    fun getSneakersInCart() : Flow<List<SneakerCart>>

    @Delete
    suspend fun deleteSneakerFromCart(sneaker: SneakerCart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSneakerInCart(sneaker: SneakerCart)

    @Query("SELECT * from sneakercart WHERE id= :id")
    suspend fun getItemById(id:Int) : List<SneakerCart>

    @Query("UPDATE sneakercart SET quantity = quantity + 1 WHERE id = :id")
    suspend fun updateSneakerInCart(id : Int)

    suspend fun insertOrUpdate(item : SneakerCart) {
        val itemsFromDB : List<SneakerCart> = getItemById(item.id)
        if (itemsFromDB.isEmpty())
            return insertSneakerInCart(item)
        else
           return updateSneakerInCart(item.id)
    }
}