package com.pimenov.feature_products_impl.di

import com.pimenov.feature_products_impl.data.repository_impl.ProductsListRepositoryImpl
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindProductsRepository(repository: ProductsListRepositoryImpl): ProductsListRepository
}
