package com.pimenov.core_network_api

import androidx.lifecycle.LiveData
import com.pimenov.core_network_api.data_object.ProductInListDTO

interface WorkerApi {
    fun getAllProduct(): LiveData<List<ProductInListDTO>?>
}
