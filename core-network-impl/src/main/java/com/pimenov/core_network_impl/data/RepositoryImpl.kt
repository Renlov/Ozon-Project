package com.pimenov.core_network_impl.data

import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.FlowDataApi
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.ServiceApi
import com.pimenov.core_network_impl.mapper.toProductDTO
import com.pimenov.core_network_impl.mapper.toProductDTOSharedPrefs
import com.pimenov.core_network_impl.mapper.toProductInListDTO
import com.pimenov.core_network_impl.mapper.toProductInListDTOSharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val productsApi: ServiceApi,
    private val database: ProductDatabase,
    private val flowDataApi: FlowDataApi
) : ProductRepository {

    override suspend fun getProductsInList() :Unit = withContext(Dispatchers.IO) {
        try {
            productsApi.getListProducts().also { productExternal->
                val cashProducts = database.getProductList().map { it.toProductInListDTO() }.toMutableList()
                cashProducts.addAll(
                    productExternal.filter { extProduct->
                        cashProducts.find {
                            it.guid == extProduct.guid
                        } == null
                    }
                )
                flowDataApi._productListSharedFlow.emit(cashProducts)
                database.addProductInList(cashProducts.map { it.toProductInListDTOSharedPrefs() })
            }
        }catch (e : Exception){
            flowDataApi._productListSharedFlow.emit(database.getProductList().map {
                it.toProductInListDTO()
            })
        }
    }

    override suspend fun getProducts() {
        productsApi.getProducts().also { productExternal ->
            val cashProduct = database.getProducts().map {
                it.toProductDTO()
            }.toMutableList()
            cashProduct.addAll(
                productExternal.filter { extProduct->
                    cashProduct.find {
                        it.guid == extProduct.guid
                    } == null
                }
            )

            database.addProducts(cashProduct.map {
                it.toProductDTOSharedPrefs()
            })

            val cashPriceList = database.getProductList().map { it.toProductInListDTO() }.toMutableList()
            cashProduct.filter{
                it.availableCount == 0 }.map { productDTO ->
                cashPriceList.map { productInListDTO ->
                    if (productDTO.guid == productInListDTO.guid) {
                        database.availablePrice(productInListDTO.guid, 0)
                    }
                }
            }
            flowDataApi._productListSharedFlow.emit(cashPriceList)
        }
    }
}