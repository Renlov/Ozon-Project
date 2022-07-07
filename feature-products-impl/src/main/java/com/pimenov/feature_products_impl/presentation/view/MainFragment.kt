package com.pimenov.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.FragmentMainBinding
import com.pimenov.feature_products_impl.di.ProductFeatureComponent
import com.pimenov.feature_products_impl.presentation.adapters.MainAdapter
import com.pimenov.feature_products_impl.presentation.utils.autoCleared
import com.pimenov.feature_products_impl.presentation.view_models.ProductsListViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi


    private val viewModel : ProductsListViewModel by viewModels() {
        ProductFeatureComponent.productFeatureComponent!!.fabric()
    }

    private val productListAdapter  by autoCleared {
        MainAdapter(::onClick, ::onClickInCart)
    }


    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            observeViewModelState()
        }
        initList()
        binding.addActionButton.setOnClickListener {
            productNavigationApi.navigateToAdd(fragment = this)
        }
    }

    private suspend fun observeViewModelState() {
        viewLifecycleOwner.lifecycleScope.launch {
            while (isActive){
                viewModel.getData()
                delay(getMinutesFromMills(5))
            }
        }
        viewModel.productInListSharedFlow.onEach{
            productListAdapter.submitList(it)
            if (it.isNotEmpty())switchLoadingShimmer()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

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

    private fun onClickInCart(guidId: String) {
        viewModel.setInCart(guidId)
    }

    private fun switchLoadingShimmer() {
        binding.shimmerView.isVisible = false
        binding.mainRecyclerView.isVisible = true
    }


    override fun onPause() {
        if(isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
    private fun getMinutesFromMills(minutes : Int) : Long{
        return minutes.toLong() * 1000L * 60L

    }
}
