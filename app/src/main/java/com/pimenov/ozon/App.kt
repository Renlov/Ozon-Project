package com.pimenov.ozon

import android.app.Application
import com.pimenov.feature_pdp_impl.di.DaggerDataStoreComponent
import com.pimenov.feature_pdp_impl.di.DataStoreComponent


private lateinit var appComponent: DataStoreComponent
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDataStoreComponent.builder().getContext(this).build()
    }
}