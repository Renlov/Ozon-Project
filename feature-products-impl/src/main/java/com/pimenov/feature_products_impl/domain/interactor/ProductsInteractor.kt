package com.pimenov.feature_products_impl.domain.interactor

import com.pimenov.feature_products_impl.domain.mapper.toVO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import javax.inject.Inject

interface ProductsInteractor {
    fun getProducts(): List<ProductInListVO>
    fun addProduct()
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override fun getProducts(): List<ProductInListVO> {
        return repository.getProductsList().map {
            it.toVO()
        }
    }

    override fun addProduct() {
        return repository.addProduct()
    }
}
