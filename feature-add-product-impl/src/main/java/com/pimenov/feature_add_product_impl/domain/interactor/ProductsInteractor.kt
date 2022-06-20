package com.pimenov.feature_add_product_impl.domain.interactor

import com.pimenov.feature_add_product_impl.domain.repository.ProductsListRepository
import javax.inject.Inject

interface ProductsInteractor {
    fun getCountProductInList() : Int
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override fun getCountProductInList(): Int {
        return repository.getCountProductInList()
    }
}
