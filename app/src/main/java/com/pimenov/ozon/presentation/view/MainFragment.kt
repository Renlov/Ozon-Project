package com.pimenov.ozon.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.FragmentMainBinding
import com.pimenov.ozon.di.ServiceLocator
import com.pimenov.ozon.presentation.adapters.MainAdapter
import com.pimenov.ozon.presentation.utils.viewModelCreator
import com.pimenov.ozon.presentation.viewModel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModelCreator {
        MainViewModel(ServiceLocator().productsInteractor)
    }
    private lateinit var productListAdapter: MainAdapter
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelState()
        initList()
    }

    private fun observeViewModelState() {
        viewModel.productLD.observe(viewLifecycleOwner) {
            productListAdapter.updateList(it)
        }
    }

    private fun initList() {
        productListAdapter = MainAdapter(::onClick)

        binding.mainRecyclerView.apply  {
            adapter = productListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun onClick(guid : String){
        Log.d("spectra", guid)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, PDPFragment.newInstance(guid))
//            .addToBackStack(null)
//            .commit()
    }
}