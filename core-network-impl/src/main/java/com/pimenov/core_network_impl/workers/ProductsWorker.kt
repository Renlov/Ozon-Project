package com.pimenov.core_network_impl.workers


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent


class ProductsWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    val productRepository: ProductRepository = CoreNetworkComponent.initAndGet(
        DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .databaseApi(CoreDatabaseComponent.initAndGet(context)).build())!!
        .getRepository()

    override fun doWork(): Result {

        return if (productRepository.getProducts() != null) Result.success() else Result.failure()
    }
}