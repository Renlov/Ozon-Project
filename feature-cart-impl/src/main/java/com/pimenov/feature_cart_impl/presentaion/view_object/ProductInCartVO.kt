package com.pimenov.feature_cart_impl.presentaion.view_object

data class ProductInCartVO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val isInCart: Boolean
)
