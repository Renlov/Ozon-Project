package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_cart_impl.presentation.view.CartFragment
import com.pimenov.feature_products_impl.presentation.view.MainFragment
import javax.inject.Inject

class CartNavigationImpl @Inject constructor() : CartNavigationApi {
    override fun navigateToMenu(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureProductsDI(fragment.requireActivity().applicationContext)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            ?.replace(R.id.fragmentContainer, MainFragment(), MainFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != CartFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(AddFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}