package com.example.sneakersapp.presentation.cart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.sneakersapp.domain.model.SneakerCart

data class CartViewState(
    var cartItem : List<SneakerCart> = mutableListOf(),
    var isLoading : MutableState<Boolean> = mutableStateOf(false),
    var total : MutableState<Int> = mutableStateOf(0),
    var removeItemFromCart : (SneakerCart) -> Unit
)
