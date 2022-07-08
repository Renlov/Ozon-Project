package com.pimenov.feature_pdp_impl.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pimenov.core_utils.recyclerUtils.inflate
import com.pimenov.feature_pdp_impl.R
import com.pimenov.feature_pdp_impl.databinding.ItemAdditionalParamsBinding

class AdditionalAdapter(private var parametrs: Map<String, String> = emptyMap()
): RecyclerView.Adapter<AdditionalAdapter.AdditioinalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditioinalViewHolder {
        return AdditioinalViewHolder(parent.inflate(ItemAdditionalParamsBinding::inflate))
    }

    override fun onBindViewHolder(holder: AdditioinalViewHolder, position: Int) {
        holder.bind(parametrs.toList()[position])
    }

    override fun getItemCount(): Int = parametrs.size

    fun submitList(newImages: Map<String, String>) {
        parametrs = newImages
        notifyDataSetChanged()
    }

    inner class AdditioinalViewHolder(private val binding: ItemAdditionalParamsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(parametrs : Pair<String, String>){
            with(binding){
                nameTextView.text = binding.root.context.getString(R.string.divideString, parametrs.first)
                descriptionTextView.text = parametrs.second
            }
        }
    }
}