package com.pimenov.core_network_impl.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_api.ServiceApi
import com.pimenov.core_network_impl.mapper.toProductDTOSharedPrefs
import com.pimenov.core_network_impl.mapper.toProductInListDTO
import com.pimenov.core_network_impl.mapper.toProductInListDTOSharedPrefs
import com.pimenov.core_network_impl.workers.ProductsWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val productsApi: ServiceApi,
    private val database: ProductDatabase,
) : ProductRepository {

    private val _productListStateFlow = MutableSharedFlow<List<ProductInListDTO>?>()
    override val productListStateFlow: SharedFlow<List<ProductInListDTO>?> = _productListStateFlow.asSharedFlow()


    override suspend fun getProductsInList() {
        productsApi.getListProducts().execute().body()?.also {
            _productListStateFlow.emit(it)
            database.addProductInList(it.map { it.toProductInListDTOSharedPrefs() })
        } ?: _productListStateFlow.emit(database.getProductList().map { it.toProductInListDTO() })

    }

    override suspend fun getProducts() {
        productsApi.getProducts().execute().body()?.also {
            database.addProducts(it.map { it.toProductDTOSharedPrefs()})
        }
    }
}