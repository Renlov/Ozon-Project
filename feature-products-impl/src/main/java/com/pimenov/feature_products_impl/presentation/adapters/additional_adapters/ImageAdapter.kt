package com.pimenov.feature_products_impl.presentation.adapters.additional_adapters

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.pimenov.core_utils.recyclerUtils.inflate
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.ItemImageRecyclerBinding


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

    inner class ImageViewHolder(private val binding: ItemImageRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            Glide.with(binding.root).load(image)
                .placeholder(getShimmerDrawable(binding.root.context))
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.imageProductList)
        }
    }

    companion object {
        private fun getShimmerDrawable(context: Context): ShimmerDrawable {
            val shimmer = Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(context, R.color.background_color))
                .setBaseAlpha(1f)
                .setHighlightColor(ContextCompat.getColor(context, R.color.background_item))
                .setHighlightAlpha(1f)
                .setDropoff(50f)
                .build()
            val shimmerDrawable = ShimmerDrawable()
            shimmerDrawable.setShimmer(shimmer)
            return shimmerDrawable
        }
    }
}
