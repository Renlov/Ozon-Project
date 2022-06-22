package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.WorkerApi
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.data.ProductsListApiImpl
import com.pimenov.core_network_impl.data.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductApi(api : ProductsListApiImpl) : WorkerApi

    @Binds
    fun provideRepository(repository: RepositoryImpl): ProductRepository
}