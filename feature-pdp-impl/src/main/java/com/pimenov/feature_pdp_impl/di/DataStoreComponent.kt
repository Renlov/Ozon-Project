package com.pimenov.feature_pdp_impl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataStoreModule::class])
interface DataStoreComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun getContext(context: Context) : Builder
        fun build() : DataStoreComponent
    }
}