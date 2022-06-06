package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.ozon.domain.interactors.ProductsInteractorList

class AddViewModel(private val productInteractor : ProductsInteractorList) : ViewModel() {

    private val _productCount = MutableLiveData<Int>()
    val productCount: LiveData<Int> = _productCount

    fun getProductsCount(){
        _productCount.postValue(productInteractor.getProducts().size)
    }

    fun addElement(){
        productInteractor.addProduct()
        getProductsCount()
    }
}