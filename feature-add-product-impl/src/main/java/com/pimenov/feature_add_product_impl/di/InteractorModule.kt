package com.pimenov.feature_add_product_impl.di

import com.pimenov.feature_add_product_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_add_product_impl.domain.interactor.ProductsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun bindProductsInteractor(interactor: ProductsInteractorImpl): ProductsInteractor
}
