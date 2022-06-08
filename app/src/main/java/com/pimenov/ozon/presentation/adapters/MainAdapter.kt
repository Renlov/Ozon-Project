package com.pimenov.ozon.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.ItemListRecyclerBinding
import com.pimenov.ozon.presentation.utils.inflate
import com.pimenov.ozon.presentation.viewObject.ProductInListVO

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
}
