package com.pimenov.feature_products_impl.presentation.view_models

import android.util.Log
import androidx.lifecycle.*
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {

    fun getProducts() : LiveData<List<ProductInListVO>> {
        return Transformations.map(productsInteractor.getProducts()) {
            it ?: emptyList()
        }
    }
}
