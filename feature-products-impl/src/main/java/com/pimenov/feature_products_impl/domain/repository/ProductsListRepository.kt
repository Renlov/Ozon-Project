package com.pimenov.feature_products_impl.domain.repository

import androidx.lifecycle.LiveData
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


interface ProductsListRepository {
    suspend fun getData()
    val productListStateFlow : SharedFlow<List<ProductInListDO>?>
}