package com.pimenov.core_network_impl.di

import androidx.work.Worker
import com.pimenov.core_network_api.NetworkApi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApiModule::class])
interface CoreNetworkComponent : NetworkApi {
    fun inject(worker: Worker)
    fun retrofit () : Retrofit
}