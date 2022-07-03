package com.pimenov.feature_products_impl.presentation.adapters.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel

class ProductListDiffUtil : DiffUtil.ItemCallback<BaseRvModel>() {

    override fun areItemsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
        return oldItem is BaseRvModel.ProductInListRv == newItem is BaseRvModel.ProductInListRv
    }

    override fun areContentsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
        if (oldItem is BaseRvModel.ProductInListRv != newItem is BaseRvModel.ProductInListRv) return false
        return true
    }
}