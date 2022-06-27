package com.pimenov.feature_products_impl.data.repository_impl

import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productRepository: ProductRepository, private val workerApi: WorkerApi) : ProductsListRepository {

    override suspend fun getData() {
        workerApi.getAllProduct()
    }

    override fun productListStateFlow(): Flow<List<ProductInListDO>> {
        return productRepository.productListSharedFlow.map {
            it!!.map {
                it.toDO()
            }
        }
    }
}


