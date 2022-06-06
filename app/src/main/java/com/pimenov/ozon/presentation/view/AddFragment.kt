package com.pimenov.ozon.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.FragmentAddBinding
import com.pimenov.ozon.di.ServiceLocatorList
import com.pimenov.ozon.presentation.utils.viewModelCreator
import com.pimenov.ozon.presentation.viewModel.AddViewModel
import com.pimenov.ozon.presentation.viewModel.MainViewModel

class AddFragment : Fragment(R.layout.fragment_add) {
    private val viewModel: AddViewModel by viewModelCreator {
        AddViewModel(ServiceLocatorList().productsInteractor)
    }
    private val binding by viewBinding(FragmentAddBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            viewModel.addElement()
        }
    }
}