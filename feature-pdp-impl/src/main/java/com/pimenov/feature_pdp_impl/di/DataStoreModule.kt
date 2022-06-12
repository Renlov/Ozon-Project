package com.pimenov.feature_pdp_impl.di

import android.content.Context
import com.pimenov.feature_pdp_impl.data.data_store.DataStore
import dagger.Module
import dagger.Provides


@Module
class DataStoreModule {

    @Provides
    fun bindDataStore(context : Context) = DataStore(context)
}