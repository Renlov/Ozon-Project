package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.data.ProductsListManagerProductImpl
import com.pimenov.core_network_impl.data.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductApi(api : ProductsListManagerProductImpl) : WorkerManagerProduct

    @Binds
    fun bindRepository(repository: RepositoryImpl): ProductRepository
}