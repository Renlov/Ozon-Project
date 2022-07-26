package com.pimenov.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.core_utils.recyclerUtils.autoCleared
import com.pimenov.feature_pdp_api.PDPNavigationApi
import com.pimenov.feature_pdp_impl.R
import com.pimenov.feature_pdp_impl.databinding.FragmentPDPBinding
import com.pimenov.feature_pdp_impl.di.PDPFeatureComponent
import com.pimenov.feature_pdp_impl.presentation.adapters.AdditionalAdapter
import com.pimenov.feature_pdp_impl.presentation.adapters.MainAdapter
import com.pimenov.feature_pdp_impl.presentation.view_models.PDPViewModel
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO
import javax.inject.Inject


class PDPFragment : Fragment(R.layout.fragment_p_d_p) {
    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi


    private val binding by viewBinding(FragmentPDPBinding::bind)
    private var productId: String ?= null
    private var countProductInt : Int ?= null
    private lateinit var pagerSnapHelper : PagerSnapHelper

    private val viewModel: PDPViewModel by viewModels() {
        PDPFeatureComponent.pdpFeatureComponent!!.fabric()
    }

    private val imageAdapter  by autoCleared {
        MainAdapter()
    }

    private val additionalAdapter by autoCleared {
        AdditionalAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            productId = it.getString(PRODUCT_ID)
            viewModel.getCounterProduct(productId ?: "error" )
        }
        productId?.let {
            getProduct(it)
        }
        pagerSnapHelper = PagerSnapHelper()
        observeViewModelState()
        setListeners()

        with(binding) {
            recyclerImageView.adapter = imageAdapter
            recyclerImageView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            pagerSnapHelper.attachToRecyclerView(recyclerImageView)
        }
    }

    private fun setAdapter(value : Map<String, String>){
        with(binding){
            productAdditional.adapter = additionalAdapter
            productAdditional.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            additionalAdapter.submitList(value)
            productAdditional.visibility = View.VISIBLE
        }
    }

    private fun setListeners(){
        with(binding.countButton.binding){
            buttonCart.setOnClickListener {
                binding.countButton.showCounter()
                productId?.let { it1 -> setInCart(it1) }
            }
            buttonMinus.setOnClickListener {
                binding.countButton.decreaseCount(countProductInt ?: 0)
                if (binding.countButton.count == 0) viewModel.setInCart(guid = productId ?: "")
            }
            buttonPlus.setOnClickListener {
                binding.countButton.increaseCount(countProductInt ?: 0)
            }
            buy.setOnClickListener {
                pdpNavigationApi.navigateCart(fragment = this@PDPFragment)
            }
        }
    }

    private fun setInCart(guidId: String){
        viewModel.setInCart(guidId)
    }

    private fun observeViewModelState() {
        viewModel.productLiveData.observe(viewLifecycleOwner) {
            updateProduct(it)
            viewModel.incrementCounterProduct(it.guid)
            if (it.isInCart){
                binding.countButton.showCounter()
            }
        }
        viewModel.counterProductLiveData.observe(viewLifecycleOwner) {
            binding.productEntry.text = requireContext().resources.getString(
                com.pimenov.feature_pdp_impl.R.string.countEntry, viewModel.counterProductLiveData.value)
        }
        viewModel.countLiveData.observe(viewLifecycleOwner){
            if (it == 0) binding.countButton.isAvailable()
            countProductInt = it
        }
    }

    private fun updateProduct(product: ProductVO) {
        with(binding){
            imageAdapter.submitList(product.images)
            indicator.attachToRecyclerView(recyclerImageView, pagerSnapHelper)
            if (product.additionalParams.isNotEmpty()) setAdapter(product.additionalParams)
            productPrice.text = root.resources.getString(com.pimenov.feature_pdp_impl.R.string.ruble, product.price)
            productName.text = product.name
            productRating.rating = product.rating.toFloat()
            productAvailableCount.text = root.resources.getString(com.pimenov.feature_pdp_impl.R.string.available, product.availableCount)
            productDescription.text = product.description
            productWeight.text = root.resources.getString(com.pimenov.feature_pdp_impl.R.string.weightProduct, product.weight)
            productCount.text = root.resources.getString(com.pimenov.feature_pdp_impl.R.string.countAvailable, product.count)
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
