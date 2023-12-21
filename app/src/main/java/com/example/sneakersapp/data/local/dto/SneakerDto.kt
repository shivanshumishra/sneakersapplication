package com.example.sneakersapp.data.local.dto

import com.example.sneakersapp.domain.model.Sneaker

data class SneakerDto(
    val box_condition: String?,
    val brand_name: String?,
    val category: List<String>?,
    val collection_slugs: List<String>?,
    val color: String?,
    val designer: String?,
    val details: String?,
    val gender: List<String>?,
    val grid_picture_url: String?,
    val has_picture: Boolean?,
    val has_stock: Boolean?,
    val id: Int?,
    val keywords: List<Any>?,
    val main_picture_url: String?,
    val midsole: String?,
    val name: String?,
    val nickname: String?,
    val original_picture_url: String?,
    val product_template_id: Int?,
    val release_date: String?,
    val release_date_unix: Int?,
    val release_year: Int?,
    val retail_price_cents: Int?,
    val shoe_condition: String?,
    val silhouette: String?,
    val size_range: List<Double?>?,
    val sku: String?,
    val slug: String?,
    val status: String?,
    val story_html: String?,
    val upper_material: String?
)

fun SneakerDto.toSneaker() : Sneaker {
    return Sneaker(
        brand_name = brand_name ?: "BRAND NAME",
    category = category ?: emptyList(),
    color = color ?: "",
    designer = designer ?: "",
    details = details ?: "",
    gender = gender ?: emptyList(),
    grid_picture_url = grid_picture_url ?: "",
    has_picture = has_picture ?: false,
    has_stock = has_stock ?: false,
    id = id ?: -1,
    main_picture_url = main_picture_url ?: "",
    name = name ?: "NAME",
    nickname = nickname ?: "NICK NAME",
    original_picture_url = original_picture_url ?: "",
    release_date = release_date ?: "2018-04-14T23:59:59.000Z",
    release_year = release_year ?: 2018,
    retail_price_cents = retail_price_cents ?: 16000,
    shoe_condition = shoe_condition ?: "",
    size_range = size_range ,
    sku = sku ?: "",
    slug = slug ?: "",
    status = status ?: "",
    story_html = story_html ?: ""
    )
}