package com.example.sneakersapp.domain.model


data class Sneaker(
    val brand_name: String,
    val category: List<String>,
    val color: String,
    val designer: String,
    val details: String,
    val gender: List<String>,
    val grid_picture_url: String,
    val has_picture: Boolean,
    val has_stock: Boolean,
    val id: Int,
    val main_picture_url: String,
    val name: String,
    val nickname: String,
    val original_picture_url: String,
    val release_date: String,
    val release_year: Int,
    val retail_price_cents: Int,
    val shoe_condition: String,
    val size_range: List<Double?>?,
    val sku: String,
    val slug: String,
    val status: String,
    val story_html: String
) {
    fun doesMatchSearchQuery(query: String) : Boolean {
        val matchingCombination = listOf(name, "${name.first()}")

        return matchingCombination.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
