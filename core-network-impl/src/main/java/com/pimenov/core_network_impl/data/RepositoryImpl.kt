package com.pimenov.core_network_impl.data

import android.util.Log
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
    override fun getProductsInList(): List<ProductInListDTO>? {
        return productsApi.getListProducts().execute().body()?.also {
            database.addProductInList(it.map { it.toProductInListDTOSharedPrefs() })
        }?.let { database.getProductList().map { it.toProductInListDTO() } }
    }

    override fun getProducts(): List<ProductDTO>? {
        return productsApi.getProducts().execute().body()?.also {
            database.addProducts(it.map { it.toProductDTOSharedPrefs()})
        }
    }
}