package com.pimenov.ozon.domain.mapper

import com.pimenov.ozon.data.dataObject.ProductDTO
import com.pimenov.ozon.data.dataObject.ProductInListDTO
import com.pimenov.ozon.domain.domainObject.ProductDO
import com.pimenov.ozon.domain.domainObject.ProductInListDO
import com.pimenov.ozon.presentation.viewObject.ProductInListVO
import com.pimenov.ozon.presentation.viewObject.ProductVO

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