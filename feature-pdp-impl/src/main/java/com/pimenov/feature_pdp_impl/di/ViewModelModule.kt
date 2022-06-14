package com.pimenov.feature_pdp_impl.di

import androidx.lifecycle.ViewModel
import com.pimenov.core_utils.ViewModelKey
import com.pimenov.feature_pdp_impl.presentation.view_models.PDPViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(PDPViewModel::class)]
    fun bindAddViewModel(impl : PDPViewModel) : ViewModel
}