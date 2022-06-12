package com.pimenov.feature_products_impl.domain.repository

import com.pimenov.feature_products_impl.domain_object.ProductInListDO


interface ProductsListRepository {
    fun getProductsList () : List<ProductInListDO>
    fun addProduct()
}