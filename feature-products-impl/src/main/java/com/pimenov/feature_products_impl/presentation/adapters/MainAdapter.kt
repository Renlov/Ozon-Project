package com.pimenov.feature_products_impl.presentation.adapters

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.*
import com.pimenov.core_utils.recyclerUtils.inflate
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemHeaderRecyclerBinding
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.adapters.additional_adapters.ImageAdapter
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import com.pimenov.feature_products_impl.presentation.adapters.view_holders.BaseViewHolder


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
                binding.btnCart.buttonState.value = binding.btnCart.buttonState.value.copy(isLoading = true)
            }

            with(binding.imageRecycler) {
                adapter = imageAdapter
                setRecycledViewPool(viewPool)
                layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
                PagerSnapHelper().attachToRecyclerView(this)
                addItemDecoration(object : RecyclerView.ItemDecoration(){
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        outRect.set(5,0,5,0)
                    }
                })
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
                    btnCart.buttonState.value = btnCart.buttonState.value.copy(isInCart, isLoading = false)
                }
            }
        }

        override fun update(bundle: Bundle) {
            val isInCart = bundle.getBoolean(PRODUCT_IN_CART)
            binding.btnCart.buttonState.value = binding.btnCart.buttonState.value.copy(isInCart = isInCart, isLoading = false)
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

    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty() || payloads[0] !is Bundle) {
            holder.bind(currentList[position])
        } else {
            val bundle = payloads[0] as Bundle
            holder.update(bundle)
        }
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }
    class ProductListDiffUtil : DiffUtil.ItemCallback<BaseRvModel>() {

        override fun areItemsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
            return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
                oldItem.guid == newItem.guid
            } else true
        }

        override fun areContentsTheSame(oldItem: BaseRvModel, newItem: BaseRvModel): Boolean {
            return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
                        oldItem.name == newItem.name
                        && oldItem.rating == newItem.rating
                        && oldItem.isFavorite == newItem.isFavorite
                        && oldItem.price == newItem.price
                        && oldItem.isInCart == newItem.isInCart
            } else true
        }

        override fun getChangePayload(oldItem: BaseRvModel, newItem: BaseRvModel): Any? {
                return if(oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv){
                    if (oldItem.guid == newItem.guid) {
                        return if (oldItem.isInCart != newItem.isInCart) {
                            val bundle = Bundle()
                            bundle.putBoolean(PRODUCT_IN_CART, newItem.isInCart)
                            bundle
                        }
                        else {
                            super.getChangePayload(oldItem, newItem)
                        }
                    }
                    return super.getChangePayload(oldItem, newItem)
                }
            else super.getChangePayload(oldItem, newItem)
        }
    }

    private companion object{
        const val PRODUCT_IN_CART = "productInCart"
    }
}


