package com.pimenov.feature_products_impl.data.repository_impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productApi: ProductsApi) : ProductsListRepository {
    override fun getProductsList(): LiveData<List<ProductInListDO>?> {
        return Transformations.map(productApi.getProductInLists()) {
            it?.map { productInList ->
                productInList.toDO()
            }
        }
    }

    override fun addProduct() {
        return productApi.addProduct()
    }
}