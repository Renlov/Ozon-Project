package com.pimenov.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import com.pimenov.core_navigation_impl.R
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_cart_impl.presentation.view.CartFragment
import com.pimenov.feature_pdp_api.PDPNavigationApi
import com.pimenov.feature_pdp_impl.presentation.view.PDPFragment
import javax.inject.Inject

class PDPNavigationImpl @Inject constructor(): PDPNavigationApi {
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
        return if(fragment.javaClass.simpleName != PDPFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(PDPFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}