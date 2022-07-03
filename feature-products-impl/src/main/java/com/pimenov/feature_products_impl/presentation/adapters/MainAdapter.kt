package com.pimenov.feature_products_impl.presentation.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pimenov.feature_products_impl.presentation.utils.inflate
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemHeaderRecyclerBinding
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.adapters.additional_adapters.ImageAdapter
import com.pimenov.feature_products_impl.presentation.adapters.diff_util.ProductListDiffUtil
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import com.pimenov.feature_products_impl.presentation.adapters.view_holders.BaseViewHolder
import kotlinx.android.synthetic.main.item_list_recycler.view.*


class MainAdapter(private val onClick: (String) -> Unit)
    : ListAdapter<BaseRvModel, BaseViewHolder<*>>(ProductListDiffUtil()) {

    inner class ProductInLiveViewHolder(
        private val binding : ItemListRecyclerBinding,
        private val onClick: (String) -> Unit
    ) : BaseViewHolder<BaseRvModel.ProductInListRv>(binding.root){

        private var currentProduct: String? = null

        private val imageAdapter = ImageAdapter()
        init {
            itemView.setOnClickListener {
                currentProduct?.let(onClick)
            }

            with(binding.imageRecycler){
                adapter = imageAdapter
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                PagerSnapHelper().attachToRecyclerView(this)
            }
        }

        override fun bindModel(model: BaseRvModel.ProductInListRv) {
            currentProduct = model.guid
            with(binding) {
                imageAdapter.submitList(model.image)
                priceProductList.text = binding.root.resources.getString(R.string.ruble, model.price)
                nameProductList.text = model.name
                ratingProductList.rating = model.rating
            }
        }
    }

    inner class HeaderViewHolder(private val binding : ItemHeaderRecyclerBinding)
        : BaseViewHolder<BaseRvModel.HeaderRv>(binding.root){
        override fun bindModel(model: BaseRvModel.HeaderRv) {
            binding.headerTitle.text = model.header
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType){
            R.layout.item_list_recycler -> ProductInLiveViewHolder(parent.inflate(ItemListRecyclerBinding::inflate), onClick)
            R.layout.item_header_recycler -> HeaderViewHolder(parent.inflate(ItemHeaderRecyclerBinding::inflate))
            else -> throw Exception()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }
}


