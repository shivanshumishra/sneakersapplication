package com.example.sneakersapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SneakerCart(
    val brand_name: String,
    val color: String,
    val designer: String,
    val details: String,
    val grid_picture_url: String,
    val has_picture: Boolean,
    val name: String,
    val nickname: String,
    val original_picture_url: String,
    val release_date: String,
    val release_year: Int,
    val retail_price_cents: Int,
    @PrimaryKey val id: Int,
    val quantity : Int = 0
)
