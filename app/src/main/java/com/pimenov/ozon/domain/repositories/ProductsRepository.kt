package com.pimenov.ozon.domain.repositories

import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.presentation.viewObject.Product
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

interface ProductsRepository {
    fun getProducts(): List<ProductInListPresentation>
    fun getProductById(guid: String): ProductInListPresentation
}