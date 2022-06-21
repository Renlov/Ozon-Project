package com.pimenov.feature_pdp_impl.data.mapper

import com.pimenov.core_datastore_api.data.data_object.ProductPrefs
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO

fun ProductPrefs.toDO() : ProductDO =
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

