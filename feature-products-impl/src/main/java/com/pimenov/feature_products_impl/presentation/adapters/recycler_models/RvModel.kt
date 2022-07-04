package com.pimenov.feature_products_impl.presentation.adapters.recycler_models

import com.pimenov.feature_products_impl.R

sealed class BaseRvModel(val viewType : Int) {
    data class HeaderRv(val header: String) : BaseRvModel(R.layout.item_header_recycler)

    data class ProductInListRv(
        val guid: String,
        val image: List<String>,
        val name: String,
        val price: String,
        val rating: Float,
        val isFavorite: Boolean,
        val isInCart: Boolean
    ) : BaseRvModel(R.layout.item_list_recycler)
}
