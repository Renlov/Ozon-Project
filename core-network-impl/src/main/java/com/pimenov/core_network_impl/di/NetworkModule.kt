package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.ProductApi
import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.core_network_impl.data.ProductApiImpl
import com.pimenov.core_network_impl.data.ProductsListApiImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductApi(api : ProductApiImpl) : ProductApi

    @Binds
    fun bindProductsListApi(api : ProductsListApiImpl) : ProductsListApi
}