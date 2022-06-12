package com.pimenov.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pimenov.feature_pdp_impl.domain.interactor.PDPInteractor
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PDPViewModel @Inject constructor(private val pdpInteractor: PDPInteractor) : ViewModel() {

    private val _productLiveData = MutableLiveData<ProductVO>()
    val productLiveData: LiveData<ProductVO> = _productLiveData


    private val _counterLiveData = MutableLiveData<Int>()
    val counterLiveData: LiveData<Int> = _counterLiveData


    fun getProductByGuid(guid : String) {
        _productLiveData.postValue(pdpInteractor.getProductById(guid))
    }



//    fun incrementCounter(guid: String) {
//        viewModelScope.launch {
//            interactor.incrementCounter(guid)
//        }
//    }
//
//    fun getCounter(guid: String) {
//        viewModelScope.launch {
//            _counterLiveData.postValue(interactor.getCounter(guid))
//        }
//    }
}
