package com.pimenov.ozon.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.FragmentAddBinding
import com.pimenov.ozon.di.ServiceLocator
import com.pimenov.ozon.presentation.utils.viewModelCreator
import com.pimenov.ozon.presentation.viewModel.AddViewModel

class AddFragment : Fragment(R.layout.fragment_add) {
    private val viewModel: AddViewModel by viewModelCreator {
        AddViewModel(ServiceLocator(requireContext()).productsInteractorList)
    }
    private val binding by viewBinding(FragmentAddBinding::bind)
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
}