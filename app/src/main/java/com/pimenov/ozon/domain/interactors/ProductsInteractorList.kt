package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

interface ProductsInteractorList {
    fun getProducts(): List<ProductInListPresentation>
}