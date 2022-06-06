package com.pimenov.ozon.domain.repositories

import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

interface ProductsRepositoryList {
    fun getProducts() : List<ProductInListPresentation>
    fun addProduct()
}