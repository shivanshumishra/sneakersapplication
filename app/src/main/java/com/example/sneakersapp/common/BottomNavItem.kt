package com.example.sneakersapp.common

import com.example.sneakersapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String) {
    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object Cart: BottomNavItem("Cart",R.drawable.ic_cart_foreground,"cart")
}
