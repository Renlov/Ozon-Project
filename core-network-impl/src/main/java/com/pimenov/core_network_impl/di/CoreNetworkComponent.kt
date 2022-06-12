package com.pimenov.core_network_impl.di

import com.pimenov.core_network_api.NetworkApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreNetworkComponent : NetworkApi