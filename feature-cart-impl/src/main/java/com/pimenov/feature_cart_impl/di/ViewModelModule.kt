package com.pimenov.feature_cart_impl.di

import androidx.lifecycle.ViewModel
import com.pimenov.core_utils.ViewModelKey
import com.pimenov.feature_cart_impl.presentation.view_model.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(CartViewModel::class)]
    fun bindAddViewModel(impl : CartViewModel) : ViewModel
}