package com.pimenov.core_network_impl.workers

import android.content.Context
import androidx.work.*
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent


class ProductInListWorker(
    context: Context,
    parameters: WorkerParameters,
) : Worker(context, parameters) {

    private val productRepository: ProductRepository = CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .databaseApi(CoreDatabaseComponent.initAndGet(context)).build(), WorkManager.getInstance(context))!!
        .getRepository()

    override fun doWork(): Result {
        return if (productRepository.getProductsInList() != null) Result.success() else Result.retry()

    }
}