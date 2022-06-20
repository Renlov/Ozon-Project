package com.pimenov.core_datastore_api.data.data_object

data class ProductInListDTO(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)