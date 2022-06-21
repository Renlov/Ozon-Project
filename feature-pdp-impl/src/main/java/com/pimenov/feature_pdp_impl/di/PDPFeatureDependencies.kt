package com.pimenov.feature_pdp_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): ProductsApi
    fun pdpNavigation(): PDPNavigationApi
    fun productDatabase() : ProductDatabase
}
