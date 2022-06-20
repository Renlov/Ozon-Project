package com.pimenov.core_network_impl.workers


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class ProductsWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {
//        DaggerCoreNetworkComponent.create().inject(this)
//        val retrofit = DaggerCoreNetworkComponent.create().retrofit()
//        val serviceApi = retrofit.create(ServiceApi::class.java)
//
//        val response = serviceApi.getProducts().execute()
//        return if (response.isSuccessful) {
//            val newList = Gson().toJson(response.body())
//            val data = Data.Builder()
//                .putString(WorkerKeys.KEY_RESPONSE_PRODUCTS, newList)
//                .build()
//            Result.success(data)
//        } else return Result.retry()
        return Result.success()
    }
}