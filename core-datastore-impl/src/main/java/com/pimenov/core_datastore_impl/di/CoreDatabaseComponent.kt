package com.pimenov.core_datastore_impl.di

import android.content.Context
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class])
@Singleton
interface CoreDatabaseComponent : DatabaseApi {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun context(context: Context): Builder
        fun build():CoreDatabaseComponent
    }
}
