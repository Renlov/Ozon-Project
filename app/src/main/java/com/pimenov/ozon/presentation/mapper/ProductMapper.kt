package com.pimenov.ozon.presentation.mapper

import com.pimenov.ozon.data.dto.Product
import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation
import com.pimenov.ozon.presentation.viewObject.ProductPresentation

fun ProductInList.toPresentation() : ProductInListPresentation =
    ProductInListPresentation(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating.toFloat(),
        isFavorite = isFavorite,
        isInCart = isInCart
    )

fun Product.toPresentation() : ProductPresentation =
    ProductPresentation(
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