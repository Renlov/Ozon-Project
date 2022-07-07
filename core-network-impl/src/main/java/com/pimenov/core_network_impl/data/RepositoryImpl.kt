package com.pimenov.core_network_impl.data

import android.util.Log
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.FlowDataApi
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.core_network_api.ServiceApi
import com.pimenov.core_network_impl.mapper.toProductDTOSharedPrefs
import com.pimenov.core_network_impl.mapper.toProductInListDTO
import com.pimenov.core_network_impl.mapper.toProductInListDTOSharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val productsApi: ServiceApi,
    private val database: ProductDatabase,
    private val flowDataApi: FlowDataApi
) : ProductRepository {

    override suspend fun getProductsInList() :Unit = withContext(Dispatchers.IO) {
        try {
            productsApi.getListProducts().also { ptoductExternal->
                val cashProducts = database.getProductList().map { it.toProductInListDTO() }.toMutableList()
                cashProducts.addAll(
                    ptoductExternal.filter { extProduct->
                        cashProducts.find {
                            it.guid == extProduct.guid
                        } == null
                    }
                )
                flowDataApi._productListSharedFlow.emit(cashProducts)
                database.addProductInList(cashProducts.map { it.toProductInListDTOSharedPrefs() })
            }
//            val list = productsApi.getListProducts()
//            database.addProductInList(list.map { it.toProductInListDTOSharedPrefs() })
//            val listToEmit = list + database.getProductAdditional().map { it.toProductInListDTO() }
//            flowDataApi._productListSharedFlow.emit(listToEmit)
        }catch (e : Exception){
            flowDataApi._productListSharedFlow.emit(database.getProductList().map {
                it.toProductInListDTO()
            })
        }
    }

    override suspend fun getProducts() {
        database.addProducts(productsApi.getProducts().map {
            it.toProductDTOSharedPrefs()
        })
    }
}