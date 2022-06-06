package com.pimenov.ozon.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.ozon.R
import com.pimenov.ozon.databinding.FragmentMainBinding
import com.pimenov.ozon.di.ServiceLocatorList
import com.pimenov.ozon.presentation.adapters.MainAdapter
import com.pimenov.ozon.presentation.utils.autoCleared
import com.pimenov.ozon.presentation.utils.viewModelCreator
import com.pimenov.ozon.presentation.viewModel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModelCreator {
        MainViewModel(ServiceLocatorList().productsInteractor)
    }
    private val productListAdapter  by autoCleared {
        MainAdapter(::onClick)
    }
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelState()
        initList()
        viewModel.getProducts()
        binding.addActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    private fun observeViewModelState() {
        viewModel.productLD.observe(viewLifecycleOwner) {
            productListAdapter.updateList(it)
        }
    }

    private fun initList() {
        binding.mainRecyclerView.apply  {
            adapter = productListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.line)!!)
            addItemDecoration(itemDecorator)
        }
    }
    private fun onClick(guid : String){
        val action = MainFragmentDirections.actionMainFragmentToPDPFragment(guid)
        findNavController().navigate(action)
    }
}