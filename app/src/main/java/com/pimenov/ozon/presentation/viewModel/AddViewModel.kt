package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.ozon.domain.interactors.ProductsInteractorList

class AddViewModel(private val productsInteractorList : ProductsInteractorList) : ViewModel() {
    private val _productCountLivaData = MutableLiveData<Int>()
    val productCountLivaData: LiveData<Int> = _productCountLivaData

    fun getProductsCount(){
        _productCountLivaData.postValue(productsInteractorList.getProducts().size)
    }

    fun addElement(){
        productsInteractorList.addProduct()
        getProductsCount()
    }
}