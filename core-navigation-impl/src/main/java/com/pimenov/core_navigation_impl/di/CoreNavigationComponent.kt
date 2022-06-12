package com.pimenov.core_navigation_impl.di

import com.pimenov.core_navigation_api.NavigationApi
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class])
@Singleton
interface CoreNavigationComponent: NavigationApi
