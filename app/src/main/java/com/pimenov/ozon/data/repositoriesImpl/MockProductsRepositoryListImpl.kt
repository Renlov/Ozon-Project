package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.domain.domainObject.ProductInListDO
import com.pimenov.ozon.data.mapper.toDO
import com.pimenov.ozon.domain.repositories.ProductsRepositoryList
import java.util.*

class MockProductsRepositoryListImpl : ProductsRepositoryList {
    override fun getProducts() : List<ProductInListDO> {
        return dataListMock.map { it.toDO() }
    }

    override fun addProduct() {
        val product = dataListMock.random()
        val newGuid = UUID.randomUUID().toString()

        dataMock.add(dataMock.find { it.guid == product.guid }?.copy(guid = newGuid) ?: error("error"))
        val newProduct = product.copy(guid = newGuid)
        dataListMock.add(newProduct)
    }
}