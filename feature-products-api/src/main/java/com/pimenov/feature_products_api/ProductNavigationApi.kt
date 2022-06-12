package com.pimenov.feature_products_api

import androidx.fragment.app.Fragment


interface ProductNavigationApi {
    fun navigateToPDP(fragment: Fragment, guid: String)
    fun isFeatureClosed(fragment: Fragment): Boolean
}