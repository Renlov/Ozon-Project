package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductInListDTO


interface ProductsListApi {
    fun getProducts(): List<ProductInListDTO>
    fun addProduct()
}
