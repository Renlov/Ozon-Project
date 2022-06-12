package com.pimenov.feature_products_impl.di

import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productsApi():ProductsListApi
    fun productNavigationApi(): ProductNavigationApi
}