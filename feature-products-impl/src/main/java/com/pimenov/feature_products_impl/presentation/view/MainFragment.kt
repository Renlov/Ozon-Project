package com.pimenov.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.FragmentMainBinding
import com.pimenov.feature_products_impl.di.ProductFeatureComponent
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.adapters.MainAdapter
import com.pimenov.feature_products_impl.presentation.utils.autoCleared
import com.pimenov.feature_products_impl.presentation.view_models.ProductsListViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi


    private val viewModel : ProductsListViewModel by viewModels() {
        ProductFeatureComponent.productFeatureComponent!!.fabric()
    }

    private val productListAdapter  by autoCleared {
        MainAdapter(::onClick)
    }
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = runBlocking {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            observeViewModelState()
        }
        initList()
        binding.addActionButton.setOnClickListener {
            productNavigationApi.navigateToAdd(fragment = requireParentFragment())
        }
    }

    private suspend fun observeViewModelState() {
        viewModel.getProducts().observe(viewLifecycleOwner){
            productListAdapter.submitList(it)
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
        productNavigationApi.navigateToPDP(fragment = this, guid = guid)
    }

    override fun onPause() {
        if(isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}
