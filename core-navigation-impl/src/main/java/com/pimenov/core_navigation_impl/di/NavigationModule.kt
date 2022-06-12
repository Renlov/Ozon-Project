package com.pimenov.core_navigation_impl.di

import com.pimenov.core_navigation_impl.navigation.PDPNavigationImpl
import com.pimenov.core_navigation_impl.navigation.ProductNavigationImpl
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
}
