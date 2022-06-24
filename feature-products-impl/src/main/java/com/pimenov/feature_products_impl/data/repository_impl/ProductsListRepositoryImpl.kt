package com.pimenov.feature_products_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productRepository: ProductRepository,
                                                     private val workerApi: WorkerApi) : ProductsListRepository {

    override fun getData() {
        workerApi.getAllProduct()
    }

    override val productListLiveData: LiveData<List<ProductInListDO>?>
        get() = productRepository.productListLiveData.map {
            it?.map {
                it.toDO()
            }
        }
}