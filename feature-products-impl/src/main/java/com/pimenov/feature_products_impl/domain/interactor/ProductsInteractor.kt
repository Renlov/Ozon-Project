package com.pimenov.feature_products_impl.domain.interactor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.mapper.toVO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface ProductsInteractor {
    suspend fun getData()
    suspend fun productListStateFlow() : Flow<List<ProductInListVO>>
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override suspend fun getData() {
        repository.getData()
    }

    override suspend fun productListStateFlow(): Flow<List<ProductInListVO>> = flow{
        repository.productListStateFlow().map {
            Log.d("!!!", "ProductsInteractorImpl list is  " + it.toString())
            emit(it!!.map {
                it.toVO()
            })
        }
    }
}