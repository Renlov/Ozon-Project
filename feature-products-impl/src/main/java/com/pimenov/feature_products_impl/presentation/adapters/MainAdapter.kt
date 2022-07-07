package com.pimenov.feature_products_impl.presentation.adapters

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.*
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemHeaderRecyclerBinding
import com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding
import com.pimenov.feature_products_impl.presentation.adapters.additional_adapters.ImageAdapter
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import com.pimenov.feature_products_impl.presentation.adapters.view_holders.BaseViewHolder
import com.pimenov.feature_products_impl.presentation.utils.inflate


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
                    btnCart.renderViewState(isInCart, isLoading)
                }
            }
        }
        fun bindInCart(isInCart: Boolean){
            binding.btnCart.binding.btnCartOn.isVisible = isInCart
        }

        fun bindLoading(isLoading : Boolean){
            binding.btnCart.binding.progressBarState.isVisible = isLoading
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
                    com.pimenov.feature_products_impl.databinding.ItemListRecyclerBinding::inflate
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

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        (holder as? ProductInLiveViewHolder)?.let { curHolder ->
            val item = (currentList[position] as? BaseRvModel.ProductInListRv) ?: return
            payloads.forEach { payload ->
                when(payload){
                    PRODUCT_IN_CART -> curHolder.bindInCart(item.isInCart)
                    PRODUCT_LOADING -> curHolder.bindLoading(item.isLoading)
                    else ->super.onBindViewHolder(holder, position, payloads)
                }
            }
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
                oldItem.isInCart == newItem.isInCart
                        && oldItem.name == newItem.name
                        && oldItem.rating == newItem.rating
                        && oldItem.isFavorite
                        && newItem.isFavorite
                        && oldItem.price == newItem.price && oldItem.isLoading == newItem.isLoading
            } else true
        }

        override fun getChangePayload(oldItem: BaseRvModel, newItem: BaseRvModel): Any? {
            return if(oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv){
                if (oldItem.isInCart != newItem.isInCart) return PRODUCT_IN_CART
                if (oldItem.isLoading != newItem.isLoading) return PRODUCT_LOADING
                null
            } else null
        }
    }
    private companion object{
        const val PRODUCT_IN_CART = 1
        const val PRODUCT_LOADING = 2
    }
}


