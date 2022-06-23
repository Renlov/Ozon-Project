package com.pimenov.feature_products_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.data.mapper.toProductInListDO
import com.pimenov.feature_products_impl.data.mapper.toProductInListPrefs
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productRepository: ProductRepository) : ProductsListRepository {

    override fun getProductsList(): List<ProductInListDO>? {
        return productRepository.getProductsInList()?.map {
            it.toDO()
        }
    }
}