package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.domain.repositories.ProductsRepositoryList
import com.pimenov.ozon.presentation.mapper.toPresentation
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation
import java.util.*

class MockProductsRepositoryListImpl : ProductsRepositoryList {
    override fun getProducts() : List<ProductInListPresentation> {
        return dataListMock.map { it.toPresentation() }
    }

    override fun addProduct() {
        val product = dataListMock.random()
        val newGuid = UUID.randomUUID().toString()

        dataMock.add(dataMock.find { it.guid == product.guid }?.copy(guid = newGuid) ?: error("error"))
        val newProduct = product.copy(guid = newGuid)
        dataListMock.add(newProduct)
    }
}