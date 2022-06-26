package com.pimenov.feature_products_impl.presentation.view_models

import android.util.Log
import androidx.lifecycle.*
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {

    private var _productInListSharedFlow = MutableStateFlow<List<ProductInListVO>>(emptyList())
    val productInListSharedFlow : SharedFlow<List<ProductInListVO>> = _productInListSharedFlow

    suspend fun getData(){
        productsInteractor.getData()
    }

    init {
            productsInteractor.productListStateFlow().onEach {
                Log.e("!!!", "it = $it")
                    _productInListSharedFlow.emit(it)
            }.launchIn(viewModelScope)
    }
}
