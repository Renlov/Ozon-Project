package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface ProductRepository {
    fun getProductsInList()
    fun getProducts()
    val productListLiveData : LiveData<List<ProductInListDTO>?>
}