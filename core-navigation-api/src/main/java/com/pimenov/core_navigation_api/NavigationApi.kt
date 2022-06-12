package com.pimenov.core_navigation_api

import com.pimenov.feature_products_api.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    //fun getPDPNavigation(): PDPNavigationApi
}
