package com.pimenov.feature_products_impl.domain.interactor

import com.pimenov.feature_products_impl.domain.mapper.toVO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface ProductsInteractor {
    suspend fun getData()
    fun productListStateFlow() : Flow<List<ProductInListVO>>
    fun addToCart(guidId : String)
    fun isInCart() : Boolean
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override suspend fun getData() {
        repository.getData()
    }

    override fun productListStateFlow(): Flow<List<ProductInListVO>> {
        return repository.productListStateFlow().map {
            it?.map {
                it.toVO()
            } ?: emptyList()
        }
    }

    override fun addToCart(guidId: String) {
        repository.updateCartState(guidId)
    }

    override fun isInCart(): Boolean {
        return repository.inInCart()
    }
}