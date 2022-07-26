package com.pimenov.feature_pdp_impl.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pimenov.feature_pdp_impl.data.data_store.DataStore
import com.pimenov.feature_pdp_impl.domain.interactor.PDPInteractor
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PDPViewModel @Inject constructor(private val pdpInteractor: PDPInteractor, private val dataStore : DataStore) : ViewModel() {

    private val _productLiveData = MutableLiveData<ProductVO>()
    val productLiveData: LiveData<ProductVO> = _productLiveData

    private val _counterProductLiveData = MutableLiveData<Int>()
    val counterProductLiveData: LiveData<Int> = _counterProductLiveData

    private val _counterLiveData = MutableLiveData<Int?>()
    val countLiveData: LiveData<Int?> = _counterLiveData


    fun getProductByGuid(guid : String) {
        _productLiveData.postValue(pdpInteractor.getProductById(guid))
        _counterLiveData.postValue(pdpInteractor.getProductById(guid).availableCount)
    }

    fun incrementCounterProduct(guid: String) {
        viewModelScope.launch {
            dataStore.incrementCounter(guid)
        }
    }

    fun setInCart(guid: String){
        pdpInteractor.setInCart(guid)
    }

    fun getCounterProduct(guid: String) {
        viewModelScope.launch {
            _counterProductLiveData.postValue(dataStore.getCounter(guid))
        }
    }
}
