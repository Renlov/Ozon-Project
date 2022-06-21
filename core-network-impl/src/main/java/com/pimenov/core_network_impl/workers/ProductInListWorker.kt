package com.pimenov.core_network_impl.workers

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.data.RepositoryImpl
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.pimenov.core_network_impl.workers.keys.WorkerKeys


class ProductInListWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    val productRepository: ProductRepository = CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .databaseApi(CoreDatabaseComponent.initAndGet(context)).build())!!
        .getRepository()

    override fun doWork(): Result {
        val data = Data.Builder()
            .putString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST, Gson().toJson(productRepository.getProductsInList()))
            .build()
        return Result.success(data)
    }
}