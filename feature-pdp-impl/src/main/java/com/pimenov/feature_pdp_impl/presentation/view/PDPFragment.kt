package com.pimenov.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.pimenov.feature_pdp_api.PDPNavigationApi
import com.pimenov.feature_pdp_impl.R
import com.pimenov.feature_pdp_impl.databinding.FragmentPDPBinding
import com.pimenov.feature_pdp_impl.di.PDPFeatureComponent
import com.pimenov.feature_pdp_impl.presentation.view_models.PDPViewModel
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO
import javax.inject.Inject


class PDPFragment : Fragment(R.layout.fragment_p_d_p) {
    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi


    private val binding by viewBinding(FragmentPDPBinding::bind)
    private var productId: String ?= null

    private val viewModel: PDPViewModel by viewModels() {
        PDPFeatureComponent.pdpFeatureComponent!!.fabric()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {

            productId = it.getString(PRODUCT_ID)
            viewModel.getCounter(productId ?: "error" )
        }
        productId?.let { getProduct(it) }
        observeViewModelState()
    }

    private fun observeViewModelState() {
        viewModel.productLiveData.observe(viewLifecycleOwner) {
            updateProduct(it)
            viewModel.incrementCounter(it.guid)
        }
        viewModel.counterLiveData.observe(viewLifecycleOwner) {
            binding.productEntry.text = requireContext().resources.getString(
                R.string.countEntry, viewModel.counterLiveData.value)
        }
    }

    private fun updateProduct(product: ProductVO) {
        with(binding){
            Glide.with(requireContext()).load(product.images[0]).into(productImage)
            productPrice.text = root.resources.getString(R.string.ruble, product.price)
            productName.text = product.name
            productRating.rating = product.rating.toFloat()
            productAvailableCount.text = root.resources.getString(R.string.available, product.availableCount)
            productDescription.text = product.description
            productWeight.text = root.resources.getString(R.string.weightProduct, product.weight)
            productCount.text = root.resources.getString(R.string.countAvailable, product.count)
        }
    }

    private fun getProduct(guid: String) {
        viewModel.getProductByGuid(guid)
    }


    companion object {
        private const val PRODUCT_ID = "productId"
        @JvmStatic
        fun newInstance(productId: String) =
            PDPFragment().apply {
                arguments = Bundle().apply {
                    putString(PRODUCT_ID, productId)
                }
            }
    }

    override fun onPause() {
        if(isRemoving) {
            if (pdpNavigationApi.isFeatureClosed(this)) {
                PDPFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }


}
