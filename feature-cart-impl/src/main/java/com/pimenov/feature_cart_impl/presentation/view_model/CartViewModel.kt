package com.pimenov.feature_cart_impl.presentation.view_model

import androidx.lifecycle.ViewModel
import com.pimenov.feature_cart_impl.domain.interactors.CartInteractor
import com.pimenov.feature_cart_impl.presentation.view_object.ProductInCartVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(private val interactor: CartInteractor) : ViewModel() {
    private var _productCartSharedFlow = MutableStateFlow<List<ProductInCartVO?>>(emptyList())
    val productCartSharedFlow : SharedFlow<List<ProductInCartVO?>> = _productCartSharedFlow

    init {
        updateData()
    }
    fun buyProduct(){
        interactor.deleteAllProductCart()
    }

    fun deleteProduct(position : Int){
        _productCartSharedFlow.value[position]?.guid?.let {
            interactor.deleteCurrentProductCart(it)
        }
        updateData()
    }

    private fun updateData(){
        CoroutineScope(Dispatchers.IO).launch {
            _productCartSharedFlow.emit(interactor.getProductCart())
        }
    }
}