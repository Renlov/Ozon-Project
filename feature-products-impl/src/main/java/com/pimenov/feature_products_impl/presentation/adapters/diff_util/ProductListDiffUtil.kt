package com.pimenov.feature_products_impl.presentation.adapters.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel

class ProductListDiffUtil : DiffUtil.ItemCallback<BaseRvModel>() {

    override fun areItemsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
        return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
            oldItem.guid == newItem.guid
        } else true
    }

    override fun areContentsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
        return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
            oldItem.isInCart == newItem.isInCart
                    && oldItem.name == newItem.name
                    && oldItem.rating == newItem.rating
                    && oldItem.isFavorite
                    && newItem.isFavorite
                    && oldItem.price == newItem.price && oldItem.isLoading == newItem.isLoading
        } else true
    }

    override fun getChangePayload(oldItem: BaseRvModel, newItem: BaseRvModel): Any? {
        return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
            oldItem.guid == newItem.guid && oldItem.isInCart == newItem.isInCart && oldItem.isLoading == newItem.isLoading
        } else true
    }
}