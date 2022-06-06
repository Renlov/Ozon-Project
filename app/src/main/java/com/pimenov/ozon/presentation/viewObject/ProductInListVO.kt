package com.pimenov.ozon.presentation.viewObject

data class ProductInListVO (
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Float,
    val isFavorite: Boolean,
    val isInCart: Boolean
)
