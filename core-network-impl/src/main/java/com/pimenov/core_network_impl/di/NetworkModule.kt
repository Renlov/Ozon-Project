package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.NetworkApi
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.core_network_impl.data.ProductsListApiImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductApi(api : ProductsListApiImpl) : ProductsApi
}