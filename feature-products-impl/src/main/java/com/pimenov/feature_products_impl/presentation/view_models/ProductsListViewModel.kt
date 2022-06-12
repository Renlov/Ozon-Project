package com.pimenov.feature_products_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<ProductInListVO>>()
    val productsLiveData: LiveData<List<ProductInListVO>> = _productsLiveData

    fun getProducts() {
        _productsLiveData.postValue(productsInteractor.getProducts())
    }
}
