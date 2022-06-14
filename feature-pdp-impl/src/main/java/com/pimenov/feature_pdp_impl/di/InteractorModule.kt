package com.pimenov.feature_pdp_impl.di

import com.pimenov.feature_pdp_impl.domain.interactor.PDPInteractor
import com.pimenov.feature_pdp_impl.domain.interactor.PDPInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun bindProductsInteractor(interactor: PDPInteractorImpl): PDPInteractor
}
