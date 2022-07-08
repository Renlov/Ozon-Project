package com.pimenov.feature_cart_impl.di

import com.pimenov.feature_cart_impl.data.repository_impl.CartRepositoryImpl
import com.pimenov.feature_cart_impl.domain.repository.CartRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun provideCartRepository(repository: CartRepositoryImpl) : CartRepository
}