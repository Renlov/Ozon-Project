package com.pimenov.feature_pdp_impl.domain.mapper

import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO

fun ProductDO.toVO() : ProductVO =
    ProductVO(
        guid = guid,
        name = name,
        price = price,
        description = description,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
        images = images,
        weight = weight,
        count = count,
        availableCount = availableCount,
        additionalParams = additionalParams
    )