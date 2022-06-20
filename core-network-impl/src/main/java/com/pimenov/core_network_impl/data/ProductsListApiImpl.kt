package com.pimenov.core_network_impl.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.*
import com.google.gson.GsonBuilder
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_impl.workers.ProductInListWorker
import com.pimenov.core_network_impl.workers.ProductsWorker
import com.pimenov.core_network_impl.workers.keys.WorkerKeys
import retrofit2.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsListApiImpl @Inject constructor(val workManager: WorkManager): ProductsApi {
    override fun getProductInLists() : LiveData<List<ProductInListDTO>?> {
        val requestProductList = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10,TimeUnit.SECONDS)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()

        val requestProducts = OneTimeWorkRequest.Builder(ProductsWorker::class.java).build()

        val workerManager = WorkManager.getInstance()
        workerManager.beginUniqueWork(WorkerKeys.KEY_WORKER, ExistingWorkPolicy.KEEP, requestProductList).then(requestProducts).enqueue()

        return Transformations.map(workerManager.getWorkInfoByIdLiveData(requestProductList.id)) {
            if (it != null && it.state.isFinished) {
                it.outputData.getString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST).let { mockData ->
                    GsonBuilder().create().fromJson(mockData, Array<ProductInListDTO>::class.java)
                        .toMutableList()
                }
            }else null
        }
    }

    override fun getProducts(): LiveData<List<ProductDTO>?> {
        val request = OneTimeWorkRequest.Builder(ProductInListWorker::class.java)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10,TimeUnit.SECONDS)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()
        val workerManager = WorkManager.getInstance()
        workerManager.enqueue(request)

        return Transformations.map(workerManager.getWorkInfoByIdLiveData(request.id)){
            GsonBuilder().create().fromJson(it.outputData.getString(WorkerKeys.KEY_RESPONSE_PRODUCT_LIST).toString() ,Array<ProductDTO>::class.java).toMutableList()
        }
    }

    override fun getProductById(guid: String): ProductDTO {
        return ProductDTO("aa", "aa", "rf", "ff", 4.0, false, false, arrayListOf(), 0.1, 2, 2, mapOf())
    }

    override fun addProduct() {
        Log.d("spectra", "add")
//        val product = dataListMock.random()
//        val newGuid = UUID.randomUUID().toString()
//
//        dataMock.add(dataMock.find { it.guid == product.guid }?.copy(guid = newGuid) ?: error("error"))
//        val newProduct = product.copy(guid = newGuid)
//        dataListMock.add(newProduct)
    }
}
