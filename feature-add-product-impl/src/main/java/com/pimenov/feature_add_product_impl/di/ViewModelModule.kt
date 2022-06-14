package com.pimenov.feature_add_product_impl.di

import android.view.View
import androidx.lifecycle.ViewModel
import com.pimenov.core_utils.ViewModelFactory
import com.pimenov.core_utils.ViewModelKey
import com.pimenov.feature_add_product_impl.presentation.view_models.AddViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey (AddViewModel::class)]
    fun bindAddViewModel(impl : AddViewModel) : ViewModel
}