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

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor, workerManagerProduct: WorkerManagerProduct) : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<ProductInListVO>>()
    val productsLiveData: LiveData<List<ProductInListVO>> = _productsLiveData

    init {
        workerManagerProduct.getAllProduct()
        getProducts()
    }


    private fun getProducts() {
        _productsLiveData.postValue(productsInteractor.getProducts())
    }
}
