package com.pimenov.feature_pdp_impl.data.mapper

import com.pimenov.core_network_api.data_object.ProductDTO

fun ProductDTO.toDO() : ProductDO =
    ProductDO(
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
