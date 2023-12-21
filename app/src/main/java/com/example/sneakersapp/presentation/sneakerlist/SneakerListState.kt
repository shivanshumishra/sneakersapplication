package com.example.sneakersapp.presentation.sneakerlist

import com.example.sneakersapp.domain.model.Sneaker

data class SneakerListState(
    var isLoading : Boolean = false,
    val sneakers : List<Sneaker> = emptyList(),
    val error : String = ""
)
