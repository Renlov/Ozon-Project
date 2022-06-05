package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.domain.repositories.ProductsRepository
import com.pimenov.ozon.presentation.mapper.toPresentation
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class MockProductsRepositoryImpl : ProductsRepository {
    override fun getProducts(): List<ProductInListPresentation> {
        return dataListMock.map { it.toPresentation() }
    }

    override fun getProductById(guid: String): ProductInListPresentation {
        return dataListMock.find { it.guid == guid }?.toPresentation() ?: error("error")
    }
}