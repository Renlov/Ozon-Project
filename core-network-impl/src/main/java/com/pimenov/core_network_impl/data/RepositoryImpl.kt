package com.pimenov.core_network_impl.data

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
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
) : ProductRepository {

    private val _productListSharedFlow
    = MutableSharedFlow<List<ProductInListDTO>>()
    override val productListSharedFlow: SharedFlow<List<ProductInListDTO>>
    = _productListSharedFlow.asSharedFlow()


    override suspend fun getProductsInList() :Unit = withContext(Dispatchers.IO) {
        try {
            val list = productsApi.getListProducts()
            database.addProductInList(list.map { it.toProductInListDTOSharedPrefs() })
            val listToEmit = list + database.getProductAdditional().map { it.toProductInListDTO() }
            _productListSharedFlow.emit(listToEmit)
        }catch (e : Exception){
            _productListSharedFlow.emit(database.getProductList().map {
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