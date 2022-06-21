package com.pimenov.feature_add_product_impl.domain.interactor

import com.pimenov.feature_add_product_impl.domain.repository.ProductsListRepository
import javax.inject.Inject

interface ProductsInteractor {
    fun getCountProductInList() : Int
    fun addRandomProduct()
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override fun getCountProductInList(): Int {
        return repository.getCountProductInList()
    }

    override fun addRandomProduct() {
        return repository.addRandomProduct()
    }
}
