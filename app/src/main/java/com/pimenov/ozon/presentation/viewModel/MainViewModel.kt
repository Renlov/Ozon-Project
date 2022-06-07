package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.ozon.domain.interactors.ProductsInteractorList
import com.pimenov.ozon.presentation.viewObject.ProductInListVO

class MainViewModel(private val productsInteractorList : ProductsInteractorList) : ViewModel() {
    private val _productLivaData = MutableLiveData<List<ProductInListVO>>()
    val productLivaData: LiveData<List<ProductInListVO>> = _productLivaData

    fun getProducts(){
        _productLivaData.postValue(productsInteractorList.getProducts())
    }
}