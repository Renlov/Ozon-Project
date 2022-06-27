package com.pimenov.core_network_impl.data

import com.pimenov.core_network_api.FlowDataApi
import com.pimenov.core_network_api.data_object.ProductInListDTO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class FlowDataImpl@Inject constructor() : FlowDataApi {
    override val _productListSharedFlow: MutableSharedFlow<List<ProductInListDTO>>
        get() = MutableSharedFlow<List<ProductInListDTO>>()

    override val productListSharedFlow: SharedFlow<List<ProductInListDTO>?>
        get() = _productListSharedFlow.asSharedFlow()

}