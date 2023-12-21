package com.example.sneakersapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakersapp.domain.model.SneakerCart

@Database(
    entities = [SneakerCart::class],
    version = 1
)
abstract class SneakerDatabase : RoomDatabase() {
    abstract val sneakersDao : SneakersDao

    companion object {
        const val DATABASE_NAME = "sneaker_db"
    }
}