package com.pimenov.feature_products_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.data.mapper.toProductInListDO
import com.pimenov.feature_products_impl.data.mapper.toProductInListPrefs
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productApi: WorkerManagerProduct, private val database : ProductDatabase) : ProductsListRepository {

    override fun getProductsList(): LiveData<List<ProductInListDO>> {
        return Transformations.map(productApi.getAllProduct()) { list ->
            list?.let {
                database.addProductInList(it.map { DTO -> DTO.toProductInListPrefs() })
                it.map { DTO -> DTO.toDO() }
            } ?:database.getProductList().map { it.toProductInListDO() }
        }
    }
}