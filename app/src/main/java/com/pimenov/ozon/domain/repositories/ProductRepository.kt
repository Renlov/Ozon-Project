package com.pimenov.ozon.domain.repositories

import com.pimenov.ozon.presentation.viewObject.ProductPresentation

interface ProductRepository {
    fun getProductById(guid: String): ProductPresentation
}