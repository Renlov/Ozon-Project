package com.pimenov.feature_pdp_impl.di

import android.content.Context
import com.pimenov.core_network_api.ProductApi
import com.pimenov.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): ProductApi
    fun pdpNavigation(): PDPNavigationApi
}
