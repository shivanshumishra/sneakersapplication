package com.example.sneakersapp.presentation.sneakerdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.sneakersapp.domain.model.Sneaker

data class SneakerDetailsState(
    var sneakerDetails : MutableState<Sneaker?> = mutableStateOf(null),
    var isLoading : MutableState<Boolean> = mutableStateOf(false)
)
