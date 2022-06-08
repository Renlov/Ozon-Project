package com.pimenov.ozon.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.data.dataStore.CountPrefs
import com.pimenov.ozon.presentation.viewObject.ProductVO
import kotlinx.coroutines.launch

class PDPViewModel(private val productInteractor : ProductsInteractor, private val interactor : CountPrefs) : ViewModel() {

    private val _productLiveData = MutableLiveData<ProductVO>()
    val productLiveData: LiveData<ProductVO> = _productLiveData


    private val _counterLiveData = MutableLiveData<Int>()
    val counterLiveData: LiveData<Int> = _counterLiveData


    fun getProductByGuid(guid : String) {
        _productLiveData.postValue(productInteractor.getProductById(guid))
    }

    fun incrementCounter(guid: String) {
        viewModelScope.launch {
            interactor.incrementCounter(guid)
        }
    }

    fun getCounter(guid: String) {
        viewModelScope.launch {
            _counterLiveData.postValue(interactor.getCounter(guid))
        }
    }
}