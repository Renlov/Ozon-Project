package com.pimenov.ozon.domain.repositories

import com.pimenov.ozon.domain.domainObject.ProductDO

interface ProductRepository {
    fun getProductById(guid: String): ProductDO
}