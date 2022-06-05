package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.presentation.utils.CountPrefs
import com.pimenov.ozon.presentation.viewObject.ProductPresentation
import kotlinx.coroutines.launch

class PDPViewModel(val productInteractor : ProductsInteractor, val interactor : CountPrefs) : ViewModel() {

    private val _productLD = MutableLiveData<ProductPresentation>()
    val productLD: LiveData<ProductPresentation> = _productLD


    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter


    fun getProductByGuid(guid : String) {
        _productLD.postValue(productInteractor.getProductById(guid))
    }

    fun incrementCounter(guid: String) {
        viewModelScope.launch {
            interactor.incrementCounter(guid)
        }
    }

    fun getCounter(guid: String) {
        viewModelScope.launch {
            _counter.postValue(interactor.getCounter(guid))
        }
    }
}