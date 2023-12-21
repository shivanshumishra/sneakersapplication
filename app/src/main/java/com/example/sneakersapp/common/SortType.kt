package com.example.sneakersapp.common

sealed class SortType( val sortType : String)
object ASCENDING_YEAR : SortType("Ascending")
object DESCENDING_YEAR : SortType("Descending")
object NONE_YEAR : SortType("None")