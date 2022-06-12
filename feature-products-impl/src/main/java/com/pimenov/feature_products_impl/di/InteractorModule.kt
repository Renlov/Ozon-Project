package com.pimenov.feature_products_impl.di

import com.pimenov.feature_products_impl.data.repository_impl.ProductsListRepositoryImpl
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindProductsInteractor(interactor: ProductsInteractorImpl): ProductsInteractor
}
