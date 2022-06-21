package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import com.pimenov.feature_pdp_impl.presentation.view.PDPFragment
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.presentation.view.MainFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {
    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI(fragment.requireContext())
        val newFragment = PDPFragment.newInstance(guid)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun navigateToAdd(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureAddDI(fragment.requireContext())
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainer, AddFragment(), AddFragment::class.java.simpleName)
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
