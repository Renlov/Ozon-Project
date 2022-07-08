package com.pimenov.feature_cart_impl.presentaion.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pimenov.feature_cart_impl.databinding.FragmentCartBinding
import com.pimenov.feature_cart_impl.presentaion.view_object.ProductInCartVO

private class CartAdapter() : ListAdapter<ProductInCartVO, CartAdapter.CartViewHolder>(CartDiffUtil()) {

    inner class CartViewHolder(val binding: FragmentCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        //holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    class CartDiffUtil : DiffUtil.ItemCallback<ProductInCartVO>() {

        override fun areItemsTheSame(oldItem: ProductInCartVO, newItem: ProductInCartVO): Boolean {
//            return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
//                oldItem.guid == newItem.guid
//            } else true
            return true
        }

        override fun areContentsTheSame(
            oldItem: ProductInCartVO,
            newItem: ProductInCartVO
        ): Boolean {
//            return if (oldItem is BaseRvModel.ProductInListRv && newItem is BaseRvModel.ProductInListRv) {
//                oldItem.name == newItem.name
//                        && oldItem.rating == newItem.rating
//                        && oldItem.isFavorite == newItem.isFavorite
//                        && oldItem.price == newItem.price
//                        && oldItem.isInCart == newItem.isInCart
//            } else true
            return true

        }

        override fun getChangePayload(oldItem: ProductInCartVO, newItem: ProductInCartVO): Any? {
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
            return true

        }
    }

    private companion object{
        const val PRODUCT_IN_CART = "productInCart"
    }




}