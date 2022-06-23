package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface ProductRepository {
    fun getProductsInList(): List<ProductInListDTO>?
    fun getProducts(): List<ProductDTO>?
}