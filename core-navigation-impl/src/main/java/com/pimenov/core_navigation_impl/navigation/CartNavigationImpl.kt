package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_cart_impl.presentation.view.CartFragment
import javax.inject.Inject

class CartNavigationImpl @Inject constructor() : CartNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != CartFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(AddFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}