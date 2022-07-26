package com.pimenov.feature_pdp_impl.data.data_store

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStore @Inject constructor(private val context: Context) {
    private val dataStore = context.DATASTORE

    suspend fun getCounter(guid: String): Int {
        return dataStore.data.map {
            it[intPreferencesKey(guid)] ?: 1
        }.first()
    }
    suspend fun incrementCounter(guid: String) {
        dataStore.edit {
            it[intPreferencesKey(guid)] = getCounter(guid) + 1
        }
    }

    companion object {
        private const val DATASTORE_COUNT = "counter"
        private val Context.DATASTORE: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_COUNT)
    }
}
