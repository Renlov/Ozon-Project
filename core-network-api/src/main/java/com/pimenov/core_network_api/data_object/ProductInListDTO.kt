package com.pimenov.core_network_api.data_object

data class ProductInListDTO(
    val guid: String,
    val image: List<String>,
    val isFavorite: Boolean,
    var isInCart: Boolean,
    val name: String,
    val price: String,
    val rating: Double
)