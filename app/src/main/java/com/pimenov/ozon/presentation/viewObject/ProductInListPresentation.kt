package com.pimenov.ozon.presentation.viewObject

data class ProductInListPresentation (
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)
