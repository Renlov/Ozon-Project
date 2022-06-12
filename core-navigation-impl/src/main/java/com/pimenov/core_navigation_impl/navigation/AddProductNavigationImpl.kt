package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.feature_add_product_api.AddProductNavigationApi
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import com.pimenov.feature_pdp_impl.presentation.view.PDPFragment
import javax.inject.Inject

class AddProductNavigationImpl @Inject constructor() : AddProductNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if(fragment.javaClass.simpleName != AddFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(AddFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}