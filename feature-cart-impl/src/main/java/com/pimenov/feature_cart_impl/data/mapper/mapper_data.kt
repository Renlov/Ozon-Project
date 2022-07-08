package com.pimenov.feature_cart_impl.data.mapper

import com.pimenov.core_datastore_api.data.data_object.ProductInListPrefs
import com.pimenov.feature_cart_impl.domain.domain_object.ProductInCartDO

fun ProductInListPrefs.toDO() : ProductInCartDO =
    ProductInCartDO(
        guid = guid,
        image = image,
        name = name,
        price = price,
        isInCart = isInCart)
