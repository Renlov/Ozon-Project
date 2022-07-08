package com.pimenov.feature_products_api

import androidx.fragment.app.Fragment

interface ProductNavigationApi {
    fun navigateToPDP(fragment: Fragment, guid: String)
    fun navigateToAdd(fragment: Fragment)
    fun navigateCart(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}