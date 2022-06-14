package com.pimenov.feature_add_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.feature_add_product_api.AddProductNavigationApi
import com.pimenov.feature_add_product_impl.R
import com.pimenov.feature_add_product_impl.databinding.FragmentAddBinding
import com.pimenov.feature_add_product_impl.di.AddProductFeatureComponent
import com.pimenov.feature_add_product_impl.presentation.view_models.AddViewModel
import javax.inject.Inject

class AddFragment : Fragment(R.layout.fragment_add) {

    @Inject
    lateinit var addProductNavigationApi : AddProductNavigationApi

    private val viewModel: AddViewModel by viewModels {
        AddProductFeatureComponent.productFeatureComponent!!.fabric()
    }

    private val binding by viewBinding(FragmentAddBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AddProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductsCount()
        observeViewModelState()
        binding.addButton.setOnClickListener {
            viewModel.addElement()
        }
    }

    private fun observeViewModelState() {
        viewModel.productCountLivaData.observe(viewLifecycleOwner) {
            binding.countElement.text = requireContext().resources.getString(R.string.addedElement, it)
        }
    }

    override fun onPause() {
        if(isRemoving) {
            if (addProductNavigationApi.isFeatureClosed(this)) {
                AddProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}
