package com.pimenov.feature_add_product_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.feature_add_product_api.AddProductNavigationApi

interface AddProductFeatureDependencies {
    fun productsApi(): ProductsApi
    fun addNavigation() : AddProductNavigationApi
    fun productDatabase() : ProductDatabase
}