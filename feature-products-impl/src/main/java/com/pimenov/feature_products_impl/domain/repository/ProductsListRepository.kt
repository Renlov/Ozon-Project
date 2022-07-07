package com.pimenov.feature_products_impl.domain.repository

import androidx.lifecycle.LiveData
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


interface ProductsListRepository {
    suspend fun getData()
    fun productListStateFlow() : Flow<List<ProductInListDO>?>
    fun updateCartState(guidId: String)
}