package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.presentation.view.MainFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {
    override fun navigateToPDP(fragment: Fragment, guid: String) {
        //FeatureInjectorProxy.initFeaturePDPDI()
        //val newFragment = PDPFragment.newInstance(guid)
        fragment.activity
            ?.supportFragmentManager
            ?.beginTransaction()
            //?.replace(R.id.fragmentContainer, newFragment, PDPFragment::class.java.simpleName)
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
