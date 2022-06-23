package com.pimenov.feature_add_product_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.feature_add_product_impl.domain.repository.ProductsListRepository
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productDatabase: ProductDatabase) : ProductsListRepository {
    override fun getCountProductInList(): Int {
        return productDatabase.getCountDatabase()
    }

    override fun addRandomProduct() {
        return productDatabase.addProductRandom()
    }
}