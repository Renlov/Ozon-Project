package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.presentation.viewObject.ProductInListVO

interface ProductsInteractorList {
    fun getProducts(): List<ProductInListVO>
    fun addProduct()
}