package com.pimenov.feature_products_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApiDependence() : WorkerApi
    fun productNavigationApiDependence(): ProductNavigationApi
    fun productDatabase() : ProductDatabase
    fun productRepository() : ProductRepository
}