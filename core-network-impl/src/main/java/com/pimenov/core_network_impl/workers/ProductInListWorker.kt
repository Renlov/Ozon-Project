package com.pimenov.core_network_impl.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProductInListWorker(
    context: Context,
    params: WorkerParameters) : CoroutineWorker(context, params) {

    private val productRepositoryApi: ProductRepository = CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .databaseApi(CoreDatabaseComponent.initAndGet(context)).build(), WorkManager.getInstance(context))!!
        .getRepository()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        try {
            productRepositoryApi.getProductsInList()
            Result.success()
        } catch (e: Exception){
            Result.retry()
        }
    }
}