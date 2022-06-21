package com.pimenov.feature_add_product_impl.domain.repository

interface ProductsListRepository {
    fun getCountProductInList() : Int
    fun addRandomProduct()
}