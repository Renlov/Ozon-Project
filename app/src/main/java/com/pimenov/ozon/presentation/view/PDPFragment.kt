package com.pimenov.ozon.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.FragmentPDPBinding
import com.pimenov.ozon.di.ServiceLocator
import com.pimenov.ozon.data.dataStore.CountPrefs
import com.pimenov.ozon.presentation.utils.viewModelCreator
import com.pimenov.ozon.presentation.viewModel.PDPViewModel
import com.pimenov.ozon.presentation.viewObject.ProductVO


class PDPFragment : Fragment(R.layout.fragment_p_d_p) {
    private val binding by viewBinding(FragmentPDPBinding::bind)
    private val args: PDPFragmentArgs by navArgs()
    private val viewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(ServiceLocator(requireContext()).productsInteractor, ServiceLocator(requireContext()).countPrefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.resultData.let{
            getProduct(it)
            viewModel.getCounter(it)
        }
        observeViewModelState()
        }

        private fun observeViewModelState() {
            viewModel.productLiveData.observe(viewLifecycleOwner) {
                updateProduct(it)
                viewModel.incrementCounter(it.guid)
            }
            viewModel.counterLiveData.observe(viewLifecycleOwner) {
                val entry = "заходов ${viewModel.counterLiveData.value}"
                binding.productEntry.text = entry
            }
        }

        private fun updateProduct(product: ProductVO) {
            val availableCount = "доступно ${product.availableCount}"
            val count = "остаток ${product.count}"
            val weight = "вес ${product.weight?: "0"}"
            val price = "${product.price}P"

            with(binding){
                Glide.with(requireContext()).load(product.images[0]).into(productImage)
                productPrice.text = price
                productName.text = product.name
                productRating.rating = product.rating.toFloat()
                productAvailableCount.text = availableCount
                productDescription.text = product.description
                productWeight.text = weight
                productCount.text = count

//                if (product.additionalParams.isNotEmpty()){
//                    ProductAdditional.visibility = View.VISIBLE
//                    ProductAdditional.text = product.additionalParams.toString()
//                }

            }
        }

        private fun getProduct(guid: String) {
            viewModel.getProductByGuid(guid)
        }
    }