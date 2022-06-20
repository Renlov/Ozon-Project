package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface ProductsApi {
    fun getProductInLists(): LiveData<List<ProductInListDTO>?>
    fun getProducts() : LiveData<List<ProductDTO>?>
    fun getProductById (guid: String): ProductDTO
    fun addProduct()
}
