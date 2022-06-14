package com.pimenov.feature_add_product_impl.di

import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.feature_add_product_api.AddProductNavigationApi

interface AddProductFeatureDependencies {
    fun productsApi(): ProductsListApi
    fun addNavigation() : AddProductNavigationApi
}