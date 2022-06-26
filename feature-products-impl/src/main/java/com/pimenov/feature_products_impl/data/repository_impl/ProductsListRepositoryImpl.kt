package com.pimenov.feature_products_impl.data.repository_impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.map
import com.pimenov.core_network_api.ProductRepository
import com.pimenov.core_network_api.WorkerApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productRepository: ProductRepository,
                                                     private val workerApi: WorkerApi) : ProductsListRepository {

    override suspend fun getData() {
        workerApi.getAllProduct()
    }
    private val scope = CoroutineScope(Dispatchers.IO)

    override val productListStateFlow: SharedFlow<List<ProductInListDO>?>
            get() = productRepository.productListStateFlow.map {
                        it?.map {
                            it.toDO()
                        }
                    }.shareIn(scope, started = SharingStarted.Lazily
            )
    }
