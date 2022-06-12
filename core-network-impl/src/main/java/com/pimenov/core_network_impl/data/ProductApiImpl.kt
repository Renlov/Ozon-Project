package com.pimenov.core_network_impl.data

import com.pimenov.core_network_api.ProductApi
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_impl.mock.dataMock
import javax.inject.Inject

class ProductApiImpl @Inject constructor() : ProductApi {
    override fun getProductById(guid: String): ProductDTO? {
        return dataMock.findLast {
            it.guid == guid
        }
    }
}