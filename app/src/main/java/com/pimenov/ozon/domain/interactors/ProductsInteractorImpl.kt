package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.domain.repositories.ProductRepository
import com.pimenov.ozon.presentation.viewObject.ProductPresentation

class ProductsInteractorImpl(private val productRepository : ProductRepository) : ProductsInteractor {
    override fun getProductById(guid: String): ProductPresentation {
        return productRepository.getProductById(guid)
    }
}