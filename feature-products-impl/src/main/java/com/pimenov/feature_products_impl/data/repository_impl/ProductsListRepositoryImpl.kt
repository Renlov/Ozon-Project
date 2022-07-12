package com.pimenov.feature_products_impl.data.repository_impl

import android.util.Log
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.FlowDataApi
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val flowDataApi: FlowDataApi,
                                                     private val workerApi: WorkerApi,
                                                     private val dataBase : ProductDatabase)
    : ProductsListRepository {

    override suspend fun getData() {
        workerApi.getAllProduct()
    }

    override fun productListStateFlow(): Flow<List<ProductInListDO>> {
        return flowDataApi.productListSharedFlow.map {
            it.map {
                it.toDO()
            }
        }
    }

    override fun updateCartState(guidId: String) {
        dataBase.updateCartState(guidId)
    }

    override fun inInCart(): Boolean {
        return dataBase.getProductListCart().isNotEmpty()
    }
}



