package com.pimenov.feature_products_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApiDependence() : WorkerManagerProduct
    fun productNavigationApiDependence(): ProductNavigationApi
    fun productDatabase() : ProductDatabase
}