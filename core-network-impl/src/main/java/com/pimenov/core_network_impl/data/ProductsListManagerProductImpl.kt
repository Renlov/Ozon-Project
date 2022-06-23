package com.pimenov.core_network_impl.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.*
import com.google.gson.GsonBuilder
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_impl.workers.ProductInListWorker
import com.pimenov.core_network_impl.workers.ProductsWorker
import com.pimenov.core_network_impl.workers.keys.WorkerKeys
import retrofit2.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsListManagerProductImpl @Inject constructor(private val workManager: WorkManager): WorkerManagerProduct {
    override fun getAllProduct(): LiveData<List<ProductInListDTO>?> {
        val requestProductList = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10,TimeUnit.SECONDS)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()

        val requestProducts = OneTimeWorkRequest.Builder(ProductsWorker::class.java).build()
        workManager.beginUniqueWork(WorkerKeys.KEY_WORKER, ExistingWorkPolicy.KEEP, requestProductList).then(requestProducts).enqueue()

        return Transformations.map(workManager.getWorkInfoByIdLiveData(requestProductList.id)) {
            if (it != null && it.state.isFinished) {
                it.outputData.getString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST).let { mockData ->
                    GsonBuilder().create().fromJson(mockData, Array<ProductInListDTO>::class.java)
                        .toMutableList()
                }
            }else null
        }
    }
}
