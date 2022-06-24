package com.pimenov.core_network_impl.data

import android.util.Log
import androidx.work.*
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.core_network_impl.workers.ProductInListWorker
import com.pimenov.core_network_impl.workers.ProductsWorker
import com.pimenov.core_network_impl.workers.keys.WorkerKeys
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkManagerImpl @Inject constructor(private val workManager: WorkManager):
    WorkerManagerProduct {
    override fun getAllProduct() {
        val requestProductList = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()

        val requestProducts = OneTimeWorkRequest.Builder(ProductsWorker::class.java).build()
        workManager.beginUniqueWork(
            WorkerKeys.KEY_WORKER,
            ExistingWorkPolicy.KEEP,
            requestProductList
        ).then(requestProducts).enqueue()
        Log.d("spectra", workManager.getWorkInfoById(requestProductList.id).toString())
        Log.d("spectra", workManager.getWorkInfoById(requestProducts.id).toString())

    }
}

//GsonBuilder().create().fromJson(mockData, Array<ProductInListDTO>::class.java)
//                        .toMutableList()
