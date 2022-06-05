package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.domain.repositories.ProductsRepositoryList
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class ProductsInteractorListImpl(private val productRepository : ProductsRepositoryList) : ProductsInteractorList {
    override fun getProducts(): List<ProductInListPresentation> {
        return productRepository.getProducts()
    }
}