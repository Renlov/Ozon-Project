package com.pimenov.core_network_api.data_object

data class ProductDTO(
    val guid: String,
    val name: String,
    val price: String,
    val description: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    val images: List<String>,
    val weight: Double?,
    val count: Int?,
    val availableCount: Int?,
    val additionalParams: Map<String, String>
)