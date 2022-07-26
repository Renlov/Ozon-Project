package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import com.pimenov.feature_cart_impl.presentation.view.CartFragment
import com.pimenov.feature_pdp_impl.presentation.view.PDPFragment
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.presentation.view.MainFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {
    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI(fragment.requireActivity().applicationContext)
        val newFragment = PDPFragment.newInstance(guid)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateToAdd(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureAddDI(fragment.requireActivity().applicationContext)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            ?.replace(R.id.fragmentContainer, AddFragment(), AddFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateCart(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureCartDI(fragment.requireActivity().applicationContext)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
            ?.replace(R.id.fragmentContainer, CartFragment(), CartFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != MainFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(MainFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}
