package com.pimenov.feature_products_impl.domain.mapper

import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO


fun ProductInListDO.toVO() : ProductInListVO =
    ProductInListVO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating.toFloat(),
        isFavorite = isFavorite,
        isInCart = isInCart
    )
