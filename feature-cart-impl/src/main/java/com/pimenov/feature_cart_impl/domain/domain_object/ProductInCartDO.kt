package com.pimenov.feature_cart_impl.domain.domain_object

data class ProductInCartDO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val isInCart: Boolean
)
