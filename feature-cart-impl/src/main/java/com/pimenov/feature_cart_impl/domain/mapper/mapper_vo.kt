package com.pimenov.feature_cart_impl.domain.mapper

import com.pimenov.feature_cart_impl.domain.domain_object.ProductInCartDO
import com.pimenov.feature_cart_impl.presentaion.view_object.ProductInCartVO

fun ProductInCartDO.toVO() : ProductInCartVO =
    ProductInCartVO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        isInCart = isInCart)
