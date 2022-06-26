package com.pimenov.feature_products_impl.presentation.view_models

import android.util.Log
import androidx.lifecycle.*
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {

    private var _productInListStateFlow = productsInteractor.productListStateFlow
    val productInListStateFlow : SharedFlow<List<ProductInListVO>?> = _productInListStateFlow

    init {
        Log.d("spectra", "viewModel")
        viewModelScope.launch {
            productsInteractor.getData()
        }
    }
}
