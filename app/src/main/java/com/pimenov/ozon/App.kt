package com.pimenov.ozon

import android.app.Application
import com.pimenov.ozon.di.ServiceLocator

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.init(this)
    }
}