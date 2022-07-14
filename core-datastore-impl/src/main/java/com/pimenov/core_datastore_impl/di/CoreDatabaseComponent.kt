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
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): CoreDatabaseComponent
    }

    companion object {
        @Volatile
        var coreDatabaseComponent: CoreDatabaseComponent? = null
            private set

        fun initAndGet(context: Context): CoreDatabaseComponent? {
            if (coreDatabaseComponent == null) {
                synchronized(CoreDatabaseComponent::class) {
                    coreDatabaseComponent = DaggerCoreDatabaseComponent.builder().context(context).build()
                }
            }
            return coreDatabaseComponent
        }

        fun get(): CoreDatabaseComponent? {
            if (coreDatabaseComponent == null) {
                throw RuntimeException("You must call 'initAndGet(coreDatabaseComponent: CoreDatabaseComponent)' method")
            }
            return coreDatabaseComponent
        }
    }
}
