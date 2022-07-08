package com.pimenov.feature_cart_impl.domain.interactors

import com.pimenov.feature_cart_impl.domain.mapper.toVO
import com.pimenov.feature_cart_impl.domain.repository.CartRepository
import com.pimenov.feature_cart_impl.presentaion.view_object.ProductInCartVO
import javax.inject.Inject


interface CartInteractor{
    fun getProductCart()  : List<ProductInCartVO?>
}

class CartInteractorImpl@Inject constructor(private val repository: CartRepository) : CartInteractor {
    override fun getProductCart(): List<ProductInCartVO?> {
        return repository.getProductCart().map {
            it?.toVO()
        }
    }
}
