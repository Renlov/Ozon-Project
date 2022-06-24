package com.pimenov.feature_products_impl.domain.repository

import androidx.lifecycle.LiveData
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO


interface ProductsListRepository {
    fun getData()
    val productListLiveData : LiveData<List<ProductInListDO>?>
}