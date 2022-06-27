package com.pimenov.core_network_impl.di

import androidx.work.WorkManager
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.FlowDataApi
import com.pimenov.core_network_api.NetworkApi
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.core_network_api.ProductRepository
import dagger.*
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApiModule::class],
    dependencies = [CoreNetworkDependencies::class])
interface CoreNetworkComponent : NetworkApi {

     fun getRepository(): ProductRepository
     fun getWorkManager() : WorkerApi

     @Component.Builder
    interface Builder{
        @BindsInstance
        fun workManager(workManager: WorkManager) : Builder
        fun dependencies(dependencies: CoreNetworkDependencies) : Builder
        fun build(): CoreNetworkComponent
    }

    companion object {
        @Volatile
        var coreNetworkComponent: CoreNetworkComponent? = null
            private set

        fun initAndGet(coreNetworkDependencies: CoreNetworkDependencies, workManager : WorkManager): CoreNetworkComponent? {
            if (coreNetworkComponent == null) {
                synchronized(CoreNetworkComponent::class) {
                    coreNetworkComponent = DaggerCoreNetworkComponent.builder()
                        .dependencies(coreNetworkDependencies)
                        .workManager(workManager)
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

