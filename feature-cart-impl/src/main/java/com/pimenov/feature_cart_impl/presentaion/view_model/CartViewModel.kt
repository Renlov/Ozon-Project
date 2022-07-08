package com.pimenov.feature_cart_impl.presentaion.view_model

import androidx.lifecycle.ViewModel
import com.pimenov.feature_cart_impl.domain.interactors.CartInteractor
import com.pimenov.feature_cart_impl.presentaion.view_object.ProductInCartVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(private val interactor: CartInteractor) : ViewModel() {
    private var _productCartSharedFlow = MutableStateFlow<List<ProductInCartVO?>>(emptyList())
    val productCartSharedFlow : SharedFlow<List<ProductInCartVO?>> = _productCartSharedFlow

    init {
        CoroutineScope(Dispatchers.IO).launch {
            _productCartSharedFlow.emit(interactor.getProductCart())
        }
    }
}