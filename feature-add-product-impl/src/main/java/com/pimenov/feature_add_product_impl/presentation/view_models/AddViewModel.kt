package com.pimenov.feature_add_product_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.feature_add_product_impl.domain.interactor.ProductsInteractor
import javax.inject.Inject

class AddViewModel @Inject constructor(private val productsInteractor : ProductsInteractor) : ViewModel() {
    fun getProductsCount() : LiveData<Int>{
        return productsInteractor.getCountProductInList()
    }

    fun addElement(){
        productsInteractor.addRandomProduct()
    }
}
