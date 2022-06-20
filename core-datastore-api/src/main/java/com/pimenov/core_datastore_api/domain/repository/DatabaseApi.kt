package com.pimenov.core_datastore_api.domain.repository

interface DatabaseApi {
    fun provideDatabase() :ProductDatabase
}