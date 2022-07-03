package com.pimenov.feature_products_impl.presentation.adapters.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T : BaseRvModel>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(model: BaseRvModel) {
        (model as? T)?.let {
            bindModel(it)
        }
    }

    protected abstract fun bindModel(model: T)
}