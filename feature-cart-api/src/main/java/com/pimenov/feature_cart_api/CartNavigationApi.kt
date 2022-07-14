package com.pimenov.feature_cart_api

import androidx.fragment.app.Fragment

interface CartNavigationApi {
    fun navigateToMenu(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}