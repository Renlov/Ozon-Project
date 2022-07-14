package com.pimenov.feature_products_impl.presentation.view_models

import androidx.lifecycle.*
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.adapters.mappers.toRV
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsListViewModel@Inject constructor(private val productsInteractor: ProductsInteractor) : ViewModel() {
    private var _productInListSharedFlow = MutableStateFlow<List<BaseRvModel>>(emptyList())
    val productInListSharedFlow : SharedFlow<List<BaseRvModel>> = _productInListSharedFlow

    suspend fun getData(){
        productsInteractor.getData()
    }

    init {
        productsInteractor.productListStateFlow().onEach {
            _productInListSharedFlow.emit(it.sortedBy { product ->
                product.price.toInt()
            }.toRV())
        }.launchIn(viewModelScope)
    }

    fun isInCart() : Boolean{
        return productsInteractor.isInCart()
    }

    fun getCountInCart() : Int{
        return productsInteractor.countInCart()
    }

    fun addToCart(guid: String) {
        viewModelScope.launch {
            productsInteractor.addToCart(guid)
            delay(SECOND_FOR_WAITING)
            val newData = _productInListSharedFlow.value.map {
                if (it is BaseRvModel.ProductInListRv) {
                    if (it.guid == guid) {
                        it.copy(
                            isInCart = !it.isInCart
                        )
                    }else it
                } else it
            }
            _productInListSharedFlow.value = newData
        }
    }

    companion object{
        const val SECOND_FOR_WAITING = 1000L
    }
}

