package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.domain.mapper.toVO
import com.pimenov.ozon.domain.repositories.ProductRepository
import com.pimenov.ozon.presentation.viewObject.ProductVO

class ProductsInteractorImpl(private val productRepository : ProductRepository) : ProductsInteractor {
    override fun getProductById(guid: String): ProductVO {
        return productRepository.getProductById(guid).toVO()
    }
}