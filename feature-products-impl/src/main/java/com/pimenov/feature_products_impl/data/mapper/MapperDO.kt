package com.pimenov.feature_products_impl.data.mapper

import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO

fun ProductInListDTO.toDO() : ProductInListDO =
    ProductInListDO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart)