package com.pimenov.feature_products_impl.di

import androidx.lifecycle.ViewModel
import com.pimenov.core_utils.ViewModelKey
import com.pimenov.feature_products_impl.presentation.view_models.ProductsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(ProductsListViewModel::class)]
    fun bindAddViewModel(impl : ProductsListViewModel) : ViewModel
}