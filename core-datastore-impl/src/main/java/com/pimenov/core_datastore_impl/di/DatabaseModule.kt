package com.pimenov.core_datastore_impl.di

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_datastore_impl.data.ProductDatabaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DatabaseModule {
    @Binds
    @Singleton
    fun bindDatabase(impl: ProductDatabaseImpl): ProductDatabase
}

