package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.domain.repositories.ProductsRepository
import com.pimenov.ozon.presentation.viewObject.Product
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class ProductsInteractorImpl(private val productRepository : ProductsRepository) : ProductsInteractor {
    override fun getProducts(): List<ProductInListPresentation> {
        return productRepository.getProducts()
    }

    override fun getProductById(guid: String): ProductInListPresentation {
        return productRepository.getProductById(guid)
    }
}