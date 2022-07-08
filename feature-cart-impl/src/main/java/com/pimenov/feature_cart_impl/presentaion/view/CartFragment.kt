package com.pimenov.feature_cart_impl.presentaion.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_cart_impl.R
import com.pimenov.feature_cart_impl.databinding.FragmentCartBinding
import com.pimenov.feature_cart_impl.di.CartFeatureComponent
import com.pimenov.feature_cart_impl.presentaion.view_model.CartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    @Inject
    lateinit var cartNavigationApi: CartNavigationApi

    private val viewModel: CartViewModel by viewModels {
        CartFeatureComponent.cartFeatureComponent!!.fabric()
    }

    private val binding by viewBinding(FragmentCartBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CartFeatureComponent.cartFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

    }

   private fun observe(){
       viewModel.productCartSharedFlow.onEach {
           Log.d("spectra", it.toString())
       }.launchIn(viewLifecycleOwner.lifecycleScope)
   }
    override fun onPause() {
        if(isRemoving) {
            if (cartNavigationApi.isFeatureClosed(this)) {
                CartFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}