package com.pimenov.core_network_impl.di

import androidx.work.WorkManager
import androidx.work.Worker
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.NetworkApi
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.data.RepositoryImpl
import dagger.*
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApiModule::class, Repository::class],
    dependencies = [CoreNetworkDependencies::class])
interface  CoreNetworkComponent : NetworkApi {

    fun getRepository(): ProductRepository

    companion object {
        @Volatile
        var coreNetworkComponent: CoreNetworkComponent? = null
            private set

        fun initAndGet(coreNetworkDependencies: CoreNetworkDependencies): CoreNetworkComponent? {
            if (coreNetworkComponent == null) {
                synchronized(CoreNetworkComponent::class) {
                    coreNetworkComponent = DaggerCoreNetworkComponent.builder()
                        .coreNetworkDependencies(coreNetworkDependencies)
                        .build()
                }
            }
            return coreNetworkComponent
        }

        fun get(): CoreNetworkComponent? {
            if (coreNetworkComponent == null) {
                throw RuntimeException("You must call 'initAndGet(coreNetworkDependencies: CoreNetworkDependencies)' method")
            }
            return coreNetworkComponent
        }

        fun resetComponent() {
            coreNetworkComponent = null
        }
    }

    @Singleton
    @Component(dependencies = [DatabaseApi::class])
    interface CoreNetworkDependenciesComponent : CoreNetworkDependencies
}

interface CoreNetworkDependencies {
    fun getDatabase(): ProductDatabase
}

@Module
interface Repository{
    @Binds
    fun provideRepository(repository: RepositoryImpl): ProductRepository
}

