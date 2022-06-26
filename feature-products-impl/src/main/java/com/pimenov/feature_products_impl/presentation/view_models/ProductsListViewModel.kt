package com.pimenov.feature_products_impl.presentation.view_models

import androidx.lifecycle.*
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {

    private var _productInListSharedFlow = MutableStateFlow<List<ProductInListVO>>(emptyList())
    val productInListSharedFlow : SharedFlow<List<ProductInListVO>?> = _productInListSharedFlow

    suspend fun getData(){
        productsInteractor.getData()
    }

    init {
        viewModelScope.launch {
            productsInteractor.productListStateFlow().onEach {
                if (it != null) {
                    _productInListSharedFlow.emit(it)
                }
            }
        }
    }
}
