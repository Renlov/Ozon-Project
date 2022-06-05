package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation

class MainViewModel(productInteractor : ProductsInteractor) : ViewModel() {
    private val _productLD = MutableLiveData<List<ProductInListPresentation>>()
    val productLD: LiveData<List<ProductInListPresentation>> = _productLD

    init {
        _productLD.postValue(productInteractor.getProducts())
    }
}