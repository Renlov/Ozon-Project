package com.pimenov.feature_cart_impl.domain.repository

import com.pimenov.feature_cart_impl.domain.domain_object.ProductInCartDO

interface CartRepository{
    fun getProductCart() : List<ProductInCartDO?>
    fun deleteAllProductCart()
    fun deleteCurrentProductCart(guidId : String)
}