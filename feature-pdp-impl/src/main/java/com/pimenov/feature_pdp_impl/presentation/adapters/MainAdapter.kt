package com.pimenov.feature_pdp_impl.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pimenov.feature_pdp_impl.databinding.ItemImageBinding
import com.pimenov.core_utils.recyclerUtils.inflate

class MainAdapter(private var images: List<String> = emptyList()
): RecyclerView.Adapter<MainAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent.inflate(ItemImageBinding::inflate))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun submitList(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image : String){
            Glide.with(binding.root).load(image).into(binding.imageViewRecycler)
        }
    }
}