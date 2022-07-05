package com.pimenov.feature_products_impl.presentation.view_models

import androidx.lifecycle.*
import com.pimenov.feature_products_impl.domain.interactor.ProductsInteractor
import com.pimenov.feature_products_impl.presentation.adapters.mappers.toRV
import com.pimenov.feature_products_impl.presentation.adapters.recycler_models.BaseRvModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun setInCart(guidId : String){
        viewModelScope.launch {
            _productInListSharedFlow.value = withContext(Dispatchers.IO){
                _productInListSharedFlow.value.map { model ->
                    val newModel =  if (model is BaseRvModel.ProductInListRv){
                        if (guidId == model.guid){
                            model.copy(isLoading = !model.isInCart)
                        } else model
                    } else model
                    newModel
                }
            }
            delay(1000)
            _productInListSharedFlow.value = withContext(Dispatchers.IO){
                _productInListSharedFlow.value.map { model ->
                    val newModel =  if (model is BaseRvModel.ProductInListRv){
                        if (guidId == model.guid){
                            model.copy(isInCart = true, isLoading = false)
                        } else model
                    } else model
                    newModel
                }
            }
        }
    }
}

