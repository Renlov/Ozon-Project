package com.pimenov.feature_add_product_impl.di

import com.pimenov.feature_add_product_impl.data.repository_impl.ProductsListRepositoryImpl
import com.pimenov.feature_add_product_impl.domain.repository.ProductsListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindProductsRepository(repository: ProductsListRepositoryImpl) : ProductsListRepository
}
