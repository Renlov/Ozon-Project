package com.pimenov.feature_products_impl.presentation.adapters.mappers

import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO

fun List<ProductInListVO>.toRV(): List<BaseRvModel> {
    val cheap = this.filter {
        it.price.toInt() < 100 && it.price.toInt() != 0
    }.map {
        it.toRvModel()
    }
    val expensive = this.filter {
        it.price.toInt() >= 100
    }.map {
        it.toRvModel()
    }
    val unavailable = this.filter {
        it.price.toInt() == 0
    }.map {
        it.toRvModelUnavailable()
    }

    return mutableListOf<BaseRvModel>().apply {
        add(BaseRvModel.HeaderRv(header = "Скидки"))
        addAll(cheap)
        add(BaseRvModel.HeaderRv(header = "Продукты"))
        addAll(expensive)
        if (unavailable.isEmpty()) return@apply
        add(BaseRvModel.HeaderRv(header = "Закончилось"))
        addAll(unavailable)
    }
}

fun ProductInListVO.toRvModel(): BaseRvModel.ProductInListRv =
    BaseRvModel.ProductInListRv(
        guid = guid,
        image = image,
        name = name,
        price = price,
        rating = rating,
        isFavorite = isFavorite,
        isInCart = isInCart
    )

fun ProductInListVO.toRvModelUnavailable() : BaseRvModel.ProductUnavailableRv =
    BaseRvModel.ProductUnavailableRv(
        guid, image, name, price, rating, isFavorite, isInCart
    )


