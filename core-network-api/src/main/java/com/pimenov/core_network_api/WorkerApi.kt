package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductInListDTO
import kotlinx.coroutines.flow.SharedFlow

interface WorkerApi {
    fun getAllProduct()
    val productListStateFlow : SharedFlow<List<ProductInListDTO>?>
}
