package com.pimenov.feature_products_impl.presentation.adapters.additional_adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.feature_products_impl.databinding.ItemImageRecyclerBinding
import com.pimenov.feature_products_impl.presentation.utils.inflate

class ImageAdapter(private var images: List<String> = emptyList()
): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent.inflate(ItemImageRecyclerBinding::inflate))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }



    inner class ImageViewHolder(private val binding: ItemImageRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image : String){
            Glide.with(binding.root).load(image).into(binding.imageProductList)
        }
    }
}