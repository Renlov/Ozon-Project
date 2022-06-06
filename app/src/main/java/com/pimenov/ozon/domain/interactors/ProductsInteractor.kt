package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.presentation.viewObject.ProductVO

interface ProductsInteractor {
    fun getProductById(guid: String): ProductVO
}