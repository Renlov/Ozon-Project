package com.pimenov.ozon.presentation.mapper

import com.pimenov.ozon.data.dto.ProductInList
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

fun ProductInList.toPresentation() : ProductInListPresentation =
    ProductInListPresentation(guid, image, name, price, rating, isFavorite, isInCart)