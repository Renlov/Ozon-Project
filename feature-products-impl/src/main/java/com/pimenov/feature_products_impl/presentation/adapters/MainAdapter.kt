package com.pimenov.feature_products_impl.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.feature_products_impl.presentation.utils.inflate
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO


class  MainAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<ProductInListVO, MainAdapter.ViewHolder>(ProductListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(ItemListRecyclerBinding::inflate), onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    inner class ViewHolder(
        private val binding: ItemListRecyclerBinding,
        onClick: (guid: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(currentList[absoluteAdapterPosition].guid)
            }
        }

        fun bind(data: ProductInListVO) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.image)
                    .into(imageProductList)
                priceProductList.text = binding.root.resources.getString(R.string.ruble, data.price)
                nameProductList.text = data.name
                ratingProductList.rating = data.rating
            }
        }
    }
    class ProductListDiffUtil : DiffUtil.ItemCallback<ProductInListVO>() {

        override fun areItemsTheSame(oldItem: ProductInListVO, newItem: ProductInListVO): Boolean {
            return oldItem.guid == newItem.guid
        }

        override fun areContentsTheSame(
            oldItem: ProductInListVO,
            newItem: ProductInListVO
        ): Boolean {
            if (oldItem.image != newItem.image) return false
            if (oldItem.name != newItem.name) return false
            if (oldItem.price != newItem.price) return false
            if (oldItem.rating != newItem.rating) return false
            return true
        }
    }
}

