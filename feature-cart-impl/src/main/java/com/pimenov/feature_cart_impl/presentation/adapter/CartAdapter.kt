package com.pimenov.feature_cart_impl.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.core_utils.recyclerUtils.inflate
import com.pimenov.feature_cart_impl.databinding.ItemCartBinding
import com.pimenov.feature_cart_impl.presentation.view_object.ProductInCartVO

class CartAdapter : ListAdapter<ProductInCartVO, CartAdapter.CartViewHolder>(CartDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(parent.inflate(ItemCartBinding::inflate))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model : ProductInCartVO){
            with(binding){
                //Потому что без разницы какая фотография будет в корзине, раз ты уже добавил его
                Glide.with(root).load(model.image[0]).into(imageCart)
                nameCart.text = model.name
                priceCart.text = model.price
            }
        }
    }

    class CartDiffUtil : DiffUtil.ItemCallback<ProductInCartVO>() {

        override fun areItemsTheSame(oldItem: ProductInCartVO, newItem: ProductInCartVO): Boolean {
            return oldItem.guid == newItem.guid
        }

        override fun areContentsTheSame(oldItem: ProductInCartVO, newItem: ProductInCartVO): Boolean {
            return oldItem.name == newItem.name
                        && oldItem.price == newItem.price
                        && oldItem.isInCart == newItem.isInCart
        }

//        override fun getChangePayload(oldItem: ProductInCartVO, newItem: ProductInCartVO): Any? {
//            return if(oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv){
//                if (oldItem.guid == newItem.guid) {
//                    return if (oldItem.isInCart != newItem.isInCart) {
//                        val bundle = Bundle()
//                        bundle.putBoolean(PRODUCT_IN_CART, newItem.isInCart)
//                        bundle
//                    }
//                    else {
//                        super.getChangePayload(oldItem, newItem)
//                    }
//                }
//                return super.getChangePayload(oldItem, newItem)
//            }
//            else super.getChangePayload(oldItem, newItem)
//        }
//            return true
//        }
    }

    private companion object{
        const val PRODUCT_IN_CART = "productInCart"
    }
}