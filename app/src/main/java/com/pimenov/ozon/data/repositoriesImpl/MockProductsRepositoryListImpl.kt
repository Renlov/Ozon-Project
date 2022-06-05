package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.domain.repositories.ProductsRepositoryList
import com.pimenov.ozon.presentation.mapper.toPresentation
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class MockProductsRepositoryListImpl : ProductsRepositoryList {
    override fun getProducts(): List<ProductInListPresentation> {
        return dataListMock.map { it.toPresentation() }
    }
}