package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.presentation.viewObject.ProductPresentation

interface ProductsInteractor {
    fun getProductById(guid: String): ProductPresentation
}