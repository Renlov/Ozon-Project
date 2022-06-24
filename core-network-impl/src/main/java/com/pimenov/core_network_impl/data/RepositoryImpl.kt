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
) : ProductRepository {

    private val _productListLiveData = MutableLiveData<List<ProductInListDTO>?>()
    override val productListLiveData: LiveData<List<ProductInListDTO>?> = _productListLiveData


    override fun getProductsInList() {
        productsApi.getListProducts().execute().body()?.also {
            _productListLiveData.postValue(it)
            database.addProductInList(it.map { it.toProductInListDTOSharedPrefs() })
        }?.let { database.getProductList().map { it.toProductInListDTO() } }
    }

    override fun getProducts() {
        productsApi.getProducts().execute().body()?.also {
            database.addProducts(it.map { it.toProductDTOSharedPrefs()})
        }
    }
}