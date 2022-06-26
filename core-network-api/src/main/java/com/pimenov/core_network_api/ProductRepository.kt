package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ProductRepository {
    suspend fun getProductsInList()
    suspend fun getProducts()
    val productListStateFlow : SharedFlow<List<ProductInListDTO>?>
}