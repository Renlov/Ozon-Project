package com.pimenov.ozon.domain.interactors

import com.pimenov.ozon.data.dataStore.CountPrefs
import com.pimenov.ozon.domain.mapper.toVO
import com.pimenov.ozon.domain.repositories.ProductsRepositoryList
import com.pimenov.ozon.presentation.viewObject.ProductInListVO

class ProductsInteractorListImpl(private val productRepository : ProductsRepositoryList) : ProductsInteractorList {
    override fun getProducts(): List<ProductInListVO> {
        return productRepository.getProducts().map {
            it.toVO()
        }
    }

    override fun addProduct() {
        return productRepository.addProduct()
    }
}