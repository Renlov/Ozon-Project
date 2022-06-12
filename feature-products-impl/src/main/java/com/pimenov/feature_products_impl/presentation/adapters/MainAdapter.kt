package com.pimenov.feature_products_impl.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.feature_products_impl.presentation.utils.inflate
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO


class  MainAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: List<ProductInListVO> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(ItemListRecyclerBinding::inflate), onClick)
    }

    fun updateList(newItems: List<ProductInListVO>) {
        items = newItems
    }

    var dataList: List<ProductInListVO> = listOf()
        set(value) {
            val diffUtil = ProductListDiffUtil(dataList, value)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: ProductInListVO = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(
        private val binding: ItemListRecyclerBinding,
        onClick: (guid: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
               items[absoluteAdapterPosition].let {
                   onClick(it.guid)
               }
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
    class ProductListDiffUtil(
        private val oldList: List<ProductInListVO>,
        private val newList: List<ProductInListVO>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].guid == newList[newItemPosition].guid &&
                    oldList[oldItemPosition].name == newList[newItemPosition].name &&
                    oldList[oldItemPosition].price == newList[newItemPosition].price &&
                    oldList[oldItemPosition].image == newList[newItemPosition].image
        }
    }
}

