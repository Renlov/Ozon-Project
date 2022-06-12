package com.pimenov.feature_add_product_impl.di

import com.pimenov.core_network_api.ProductsListApi

interface AddProductFeatureDependencies {
    fun productsApi(): ProductsListApi
}