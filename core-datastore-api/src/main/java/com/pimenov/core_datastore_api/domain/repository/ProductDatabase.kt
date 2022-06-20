package com.pimenov.core_datastore_api.domain.repository

import com.pimenov.core_datastore_api.data.data_object.ProductDTO
import com.pimenov.core_datastore_api.data.data_object.ProductInListDTO

interface ProductDatabase {
    fun getProductList() : List<ProductInListDTO>
    fun getProducts() : List<ProductDTO>
    fun addProduct()
}