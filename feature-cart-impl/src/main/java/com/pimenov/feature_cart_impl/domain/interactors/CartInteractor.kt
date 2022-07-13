package com.pimenov.feature_cart_impl.domain.interactors

import com.pimenov.feature_cart_impl.domain.mapper.toVO
import com.pimenov.feature_cart_impl.domain.repository.CartRepository
import com.pimenov.feature_cart_impl.presentation.view_object.ProductInCartVO
import javax.inject.Inject


interface CartInteractor{
    fun getProductCart()  : List<ProductInCartVO?>
    fun deleteAllProductCart()
    fun deleteCurrentProductCart(guidId : String)
}

class CartInteractorImpl@Inject constructor(private val repository: CartRepository) : CartInteractor {
    override fun getProductCart(): List<ProductInCartVO?> {
        return repository.getProductCart().map {
            it?.toVO()
        }
    }

    override fun deleteAllProductCart() {
        repository.deleteAllProductCart()
    }

    override fun deleteCurrentProductCart(guidId: String) {
        repository.deleteCurrentProductCart(guidId)
    }
}
