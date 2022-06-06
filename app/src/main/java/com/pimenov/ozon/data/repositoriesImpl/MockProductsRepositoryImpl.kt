package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.domain.repositories.ProductRepository
import com.pimenov.ozon.presentation.mapper.toPresentation
import com.pimenov.ozon.presentation.viewObject.ProductPresentation

class MockProductsRepositoryImpl : ProductRepository{
    override fun getProductById(guid: String): ProductPresentation {
        return dataMock.find { it.guid == guid }?.toPresentation() ?: error("error")
    }
}