package com.pimenov.ozon.data.mapper

import com.pimenov.ozon.data.dataObject.ProductDTO
import com.pimenov.ozon.data.dataObject.ProductInListDTO
import com.pimenov.ozon.domain.domainObject.ProductDO
import com.pimenov.ozon.domain.domainObject.ProductInListDO

fun ProductInListDTO.toDO() : ProductInListDO =
    ProductInListDO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart
    )

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