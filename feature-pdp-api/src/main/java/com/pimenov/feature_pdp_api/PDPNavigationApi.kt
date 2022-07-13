package com.pimenov.feature_pdp_api

import androidx.fragment.app.Fragment

interface PDPNavigationApi {
    fun navigateCart(fragment: Fragment)
    fun isFeatureClosed(fragment: Fragment): Boolean
}
