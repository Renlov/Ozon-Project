package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductDTO


interface ProductApi {
    fun getProductById (guid: String): ProductDTO?
}