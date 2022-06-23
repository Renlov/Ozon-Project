package com.pimenov.core_network_impl.workers

import android.content.Context
import androidx.work.*
import com.google.gson.Gson
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_impl.data.ProductRepository
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.pimenov.core_network_impl.workers.keys.WorkerKeys
import javax.inject.Inject


class ProductInListWorker(
    context: Context,
    parameters: WorkerParameters,
) : Worker(context, parameters) {

    private val productRepository: ProductRepository = CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder()
        .databaseApi(CoreDatabaseComponent.initAndGet(context)).build(), WorkManager.getInstance(context))!!
        .getRepository()

    override fun doWork(): Result {
        val data = Data.Builder()
            .putString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST, Gson().toJson(productRepository.getProductsInList()))
            .build()
        return Result.success(data)
    }
}

//class Factory @Inject constructor(private val productRepository: ProductRepository) :
//    WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker {
//        return ProductInListWorker(appContext, workerParameters, productRepository)
//    }
//}
//
