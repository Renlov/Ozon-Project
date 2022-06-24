package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.WorkerApi
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_impl.data.WorkManagerImpl
import com.pimenov.core_network_impl.data.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductApi(api : WorkManagerImpl) : WorkerApi

    @Binds
    fun bindRepository(repository: RepositoryImpl): ProductRepository
}