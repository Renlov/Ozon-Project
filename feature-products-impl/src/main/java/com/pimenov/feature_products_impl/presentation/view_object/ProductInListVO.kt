package com.pimenov.feature_products_impl.presentation.view_object

data class ProductInListVO (
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Float,
    val isFavorite: Boolean,
    val isInCart: Boolean
)
