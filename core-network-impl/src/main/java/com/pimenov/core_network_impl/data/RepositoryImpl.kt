package com.pimenov.core_network_impl.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val productsApi: ServiceApi,
    private val database: ProductDatabase,
    private val gson: Gson
) : ProductRepository {
    override suspend fun getProductsInList() : List<ProductInListDTO>? {
        return productsApi.getListProducts().execute().body()?.also {
            database.addProductInList(it.map { it.toProductInListDTOSharedPrefs() })
        }?.let { database.getProductList().map { it.toProductInListDTO() } }
    }

    override suspend fun getProducts() : List<ProductDTO>? {
        return productsApi.getProducts().execute().body()?.also {
            database.addProducts(it.map { it.toProductDTOSharedPrefs()})
        }
    }

    override suspend fun getObservableProductList(): LiveData<List<ProductInListDTO>?> {
        return MutableLiveData<List<ProductInListDTO>>(getProductsInList())
    }

    override suspend fun getObservableProducts(): LiveData<List<ProductDTO>?> {
        return MutableLiveData<List<ProductDTO>>(getProducts())
    }
}