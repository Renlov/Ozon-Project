package com.pimenov.feature_products_impl.domain.interactor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.mapper.toVO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import javax.inject.Inject

interface ProductsInteractor {
    fun getProducts(): LiveData<List<ProductInListVO>>
}

class ProductsInteractorImpl @Inject constructor(private val repository: ProductsListRepository): ProductsInteractor {
    override fun getProducts(): LiveData<List<ProductInListVO>> {
        return Transformations.map(repository.getProductsList()) { list->
            list?.let {
                it.map { productInList ->
                    productInList.toVO()
                } ?: listOf()
            }
        }
    }
}
