package com.pimenov.core_network_impl.data

import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_impl.mock.dataListMock
import com.pimenov.core_network_impl.mock.dataMock
import java.util.*
import javax.inject.Inject

class ProductsListApiImpl @Inject constructor(): ProductsListApi {
    override fun getProducts(): List<ProductInListDTO> {
        return dataListMock
    }

    override fun addProduct() {
        val product = dataListMock.random()
        val newGuid = UUID.randomUUID().toString()

        dataMock.add(dataMock.find { it.guid == product.guid }?.copy(guid = newGuid) ?: error("error"))
        val newProduct = product.copy(guid = newGuid)
        dataListMock.add(newProduct)
    }
}
