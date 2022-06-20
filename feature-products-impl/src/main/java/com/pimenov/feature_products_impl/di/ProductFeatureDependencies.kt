package com.pimenov.feature_products_impl.di

import com.pimenov.core_network_api.ProductsApi
import com.pimenov.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApi():ProductsApi
    fun productNavigationApi(): ProductNavigationApi
}