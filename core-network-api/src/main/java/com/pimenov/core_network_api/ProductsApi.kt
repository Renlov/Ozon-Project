package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface ProductsApi {
    fun getAllProduct(): LiveData<List<ProductInListDTO>?>
}
