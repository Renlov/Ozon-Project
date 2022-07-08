package com.pimenov.core_navigation_api

import com.pimenov.feature_add_product_api.AddProductNavigationApi
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_pdp_api.PDPNavigationApi
import com.pimenov.feature_products_api.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getPDPNavigation(): PDPNavigationApi
    fun getAddProductNavigation() : AddProductNavigationApi
    fun getCartNavigation() : CartNavigationApi
}
