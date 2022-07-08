package com.pimenov.core_navigation_impl.di

import com.pimenov.feature_add_product_api.AddProductNavigationApi
import com.pimenov.core_navigation_impl.navigation.AddProductNavigationImpl
import com.pimenov.core_navigation_impl.navigation.CartNavigationImpl
import com.pimenov.core_navigation_impl.navigation.PDPNavigationImpl
import com.pimenov.core_navigation_impl.navigation.ProductNavigationImpl
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_pdp_api.PDPNavigationApi
import com.pimenov.feature_products_api.ProductNavigationApi
import dagger.Binds
import dagger.Module


@Module
interface NavigationModule {
    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi
    @Binds
    fun bindPDPNavigation(navigation: PDPNavigationImpl): PDPNavigationApi
    @Binds
    fun bindAddProductNavigation(navigation : AddProductNavigationImpl) : AddProductNavigationApi
    @Binds
    fun bindCartNavigation(navigation : CartNavigationImpl) : CartNavigationApi
}
