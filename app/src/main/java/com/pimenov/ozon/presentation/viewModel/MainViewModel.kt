package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pimenov.ozon.domain.interactors.ProductsInteractorList
import com.pimenov.ozon.presentation.utils.CountPrefs
import com.pimenov.ozon.presentation.viewObject.ProductInListPresentation
import kotlinx.coroutines.launch

class MainViewModel(productInteractor : ProductsInteractorList) : ViewModel() {
    private val _productLD = MutableLiveData<List<ProductInListPresentation>>()
    val productLD: LiveData<List<ProductInListPresentation>> = _productLD

    init {
        _productLD.postValue(productInteractor.getProducts())
    }

}