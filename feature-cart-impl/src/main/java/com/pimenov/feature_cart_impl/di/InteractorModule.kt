package com.pimenov.feature_cart_impl.di

import com.pimenov.feature_cart_impl.domain.interactors.CartInteractor
import com.pimenov.feature_cart_impl.domain.interactors.CartInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun bindCartInteractor(intercator : CartInteractorImpl) : CartInteractor
}