package com.example.sneakersapp.presentation

sealed class Screen(val route : String) {
    object SneakerListScreen : Screen("sneaker_list_screen")
    object SneakerDetailScreen : Screen("sneaker_detail_screen")
    object CartScreen : Screen("cart_screen")
}
