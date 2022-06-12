package com.pimenov.feature_products_impl.data.repository_impl

import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain.domain_object.ProductInListDO
import javax.inject.Inject

class ProductsListRepositoryImpl @Inject constructor(private val productListApi: ProductsListApi) : ProductsListRepository {
    override fun getProductsList(): List<ProductInListDO> {
        return productListApi.getProducts().map {
            it.toDO()
        }
    }

    override fun addProduct() {
        return productListApi.addProduct()
    }
}