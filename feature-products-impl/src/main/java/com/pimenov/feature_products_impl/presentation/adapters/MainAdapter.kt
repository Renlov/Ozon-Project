package com.pimenov.feature_products_impl.presentation.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemHeaderRecyclerBinding
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.adapters.additional_adapters.ImageAdapter
import com.pimenov.feature_products_impl.presentation.adapters.diff_util.ProductListDiffUtil
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import com.pimenov.feature_products_impl.presentation.adapters.view_holders.BaseViewHolder
import com.pimenov.feature_products_impl.presentation.utils.inflate
import kotlinx.android.synthetic.main.item_list_recycler.view.*


class MainAdapter(
    private val onClick: (String) -> Unit,
    private val onClickInCart: (String) -> Unit
) : ListAdapter<BaseRvModel, BaseViewHolder<*>>(ProductListDiffUtil()) {

    inner class ProductInLiveViewHolder(
        private val binding: ItemListRecyclerBinding,
        private val onClick: (String) -> Unit
    ) : BaseViewHolder<BaseRvModel.ProductInListRv>(binding.root) {

        private var currentProduct: String? = null
        var viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
        private val imageAdapter = ImageAdapter()

        init {
            itemView.setOnClickListener {
                currentProduct?.let(onClick)
            }


            binding.btnCart.binding.btnCartOn.setOnClickListener {
                currentProduct?.let(onClickInCart)
            }

            with(binding.imageRecycler) {
                adapter = imageAdapter
                setRecycledViewPool(viewPool)
                layoutManager =
                    LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                PagerSnapHelper().attachToRecyclerView(this)
            }
        }

        override fun bindModel(model: BaseRvModel.ProductInListRv) {
            currentProduct = model.guid
            with(model) {
                with(binding) {
                    imageAdapter.submitList(image)
                    priceProductList.text = binding.root.resources.getString(R.string.ruble, price)
                    nameProductList.text = name
                    ratingProductList.rating = rating
                    btnCart.renderViewState(isInCart, isLoading)
                }
            }
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderRecyclerBinding) :
        BaseViewHolder<BaseRvModel.HeaderRv>(binding.root) {
        override fun bindModel(model: BaseRvModel.HeaderRv) {
            binding.headerTitle.text = model.header
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.item_list_recycler -> ProductInLiveViewHolder(
                parent.inflate(
                    ItemListRecyclerBinding::inflate
                ), onClick
            )
            R.layout.item_header_recycler -> HeaderViewHolder(
                parent.inflate(
                    ItemHeaderRecyclerBinding::inflate
                )
            )
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


