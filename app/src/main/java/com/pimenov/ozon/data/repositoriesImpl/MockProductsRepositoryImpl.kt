package com.pimenov.ozon.data.repositoriesImpl

import com.pimenov.ozon.data.mapper.toDO
import com.pimenov.ozon.domain.domainObject.ProductDO
import com.pimenov.ozon.domain.repositories.ProductRepository

class MockProductsRepositoryImpl : ProductRepository{
    override fun getProductById(guid: String): ProductDO {
        return dataMock.find { it.guid == guid }?.toDO() ?: error("error")
    }
}