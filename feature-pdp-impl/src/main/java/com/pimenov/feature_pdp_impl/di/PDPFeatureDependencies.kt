package com.pimenov.feature_pdp_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productsApi(): WorkerApi
    fun pdpNavigation(): PDPNavigationApi
    fun productDatabase() : ProductDatabase
}
