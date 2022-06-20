package com.pimenov.core_network_impl.mapper

import com.pimenov.core_datastore_api.data.data_object.ProductInListPrefs
import com.pimenov.core_datastore_api.data.data_object.ProductPrefs
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO

fun ProductDTO.toProductDTOSharedPrefs(): ProductPrefs {
    return ProductPrefs(
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
}

fun ProductInListDTO.toProductInListDTOSharedPrefs(): ProductInListPrefs {
    return ProductInListPrefs(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
    )
}
fun ProductInListPrefs.toProductInListDTO(): ProductInListDTO {
    return ProductInListDTO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart,
    )
}
