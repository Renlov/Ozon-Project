package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductInListDTO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface FlowDataApi {
    val productListSharedFlow : SharedFlow<List<ProductInListDTO>?>
    val _productListSharedFlow : MutableSharedFlow<List<ProductInListDTO>>
}