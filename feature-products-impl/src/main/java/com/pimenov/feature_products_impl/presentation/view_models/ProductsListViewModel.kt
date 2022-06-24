package com.pimenov.feature_products_impl.presentation.view_models

import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.Glide.init
import com.pimenov.core_network_api.ServiceApi
import com.pimenov.core_network_api.WorkerManagerProduct
import com.pimenov.feature_products_api.ProductNavigationApi
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {


    init {
        productsInteractor.getData()
    }


    fun getProducts() : LiveData<List<ProductInListVO>?> {
       return productsInteractor.productListLiveData
    }
}
