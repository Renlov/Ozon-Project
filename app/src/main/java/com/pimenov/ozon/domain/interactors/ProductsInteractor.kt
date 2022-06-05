package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.presentation.viewObject.Product
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

interface ProductsInteractor {
    fun getProducts(): List<ProductInListPresentation>
    fun getProductById(guid: String): ProductInListPresentation
}