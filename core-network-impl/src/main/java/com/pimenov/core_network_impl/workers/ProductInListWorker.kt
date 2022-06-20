package com.pimenov.core_network_impl.workers

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.pimenov.core_network_impl.workers.keys.WorkerKeys


class ProductInListWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    companion object{
        val appComponent: CoreNetworkComponent = CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder().build())!!

    }


    override fun doWork(): Result {

        val data = Data.Builder()
            .putString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST, Gson().toJson(appComponent.getRepository().getProductsInList()))
            .build()
        return Result.success(data)
    }
}