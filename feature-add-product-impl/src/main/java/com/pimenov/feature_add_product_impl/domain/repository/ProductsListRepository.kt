package com.pimenov.feature_add_product_impl.domain.repository

import androidx.lifecycle.LiveData

interface ProductsListRepository {
    fun getCountProductInList() : LiveData<Int>
    fun addRandomProduct()
}