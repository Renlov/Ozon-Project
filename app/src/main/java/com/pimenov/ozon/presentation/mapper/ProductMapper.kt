package com.pimenov.ozon.presentation.mapper

import com.pimenov.ozon.data.dto.Product
import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation
import com.pimenov.ozon.presentation.viewObject.ProductPresentation

fun ProductInList.toPresentation() : ProductInListPresentation =
    ProductInListPresentation(guid, image, name, price, rating.toFloat(), isFavorite, isInCart)

fun Product.toPresentation() : ProductPresentation =
    ProductPresentation(guid, name, price, description, rating, isFavorite, isInCart, images, weight, count, availableCount, additionalParams)