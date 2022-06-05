package com.pimenov.ozon.presentation.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.ItemListRecyclerBinding
import com.pimenov.ozon.presentation.utils.inflate
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class MainAdapter(
    private val onClick: (position: String) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: List<ProductInListPresentation> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(ItemListRecyclerBinding::inflate), onClick)
    }


    fun updateList(newItems: List<ProductInListPresentation>) {
        items = newItems
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: ProductInListPresentation = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: ItemListRecyclerBinding,
        onClick: (guid: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var guid: String? = null

        init {
            itemView.setOnClickListener {
                guid?.let { guid ->
                    onClick(guid)
                }
            }
        }

        fun bind(data: ProductInListPresentation) {
            guid = data.guid
            val priceText = data.price + "P"
            Glide.with(itemView).load(data.image)
                .into(binding.imageProductList)
            with(binding) {
                priceProductList.text = priceText
                nameProductList.text = data.name
                ratingProductList.rating = data.rating.toFloat()
            }

        }
    }
}
