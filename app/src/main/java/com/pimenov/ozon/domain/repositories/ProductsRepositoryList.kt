package com.pimenov.ozon.domain.repositories

import com.pimenov.ozon.domain.domainObject.ProductInListDO

interface ProductsRepositoryList {
    fun getProducts() : List<ProductInListDO>
    fun addProduct()
}