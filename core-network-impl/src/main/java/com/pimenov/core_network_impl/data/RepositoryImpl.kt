package com.pimenov.core_network_impl.data

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_impl.mapper.toProductDTOSharedPrefs
import com.pimenov.core_network_impl.mapper.toProductInListDTO
import com.pimenov.core_network_impl.mapper.toProductInListDTOSharedPrefs
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val productsApi: ServiceApi,
    private val database: ProductDatabase
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

interface ProductRepository {
    fun getProductsInList(): List<ProductInListDTO>?
    fun getProducts(): List<ProductDTO>?
}
