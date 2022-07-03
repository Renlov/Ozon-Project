package com.pimenov.core_datastore_api.data.data_object

data class ProductInListPrefs(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)