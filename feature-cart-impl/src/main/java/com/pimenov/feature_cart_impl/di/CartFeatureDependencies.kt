package com.pimenov.feature_cart_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.feature_cart_api.CartNavigationApi

interface CartFeatureDependencies {
    fun productDatabase() : ProductDatabase
    fun cartNavigation() : CartNavigationApi
}