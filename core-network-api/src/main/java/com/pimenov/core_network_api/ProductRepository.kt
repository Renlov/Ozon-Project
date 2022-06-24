package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface ProductRepository {
    suspend fun getProductsInList(): List<ProductInListDTO>?
    suspend fun getProducts(): List<ProductDTO>?

    suspend fun getObservableProductList() : LiveData<List<ProductInListDTO>?>
    suspend fun getObservableProducts() : LiveData<List<ProductDTO>?>
}