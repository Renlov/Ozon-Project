package com.pimenov.feature_cart_impl.data.repository_impl

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.feature_cart_impl.data.mapper.toDO
import com.pimenov.feature_cart_impl.domain.domain_object.ProductInCartDO
import com.pimenov.feature_cart_impl.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl@Inject constructor(private val database: ProductDatabase) : CartRepository {
    override fun getProductCart(): List<ProductInCartDO?> {
        return database.getProductListCart().map {
            it?.toDO()
        }
    }

    override fun deleteAllProductCart() {
        database.buyAllProductCart()
    }

    override fun deleteCurrentProductCart(guidId: String) {
        database.updateCartState(guidId)
    }
}