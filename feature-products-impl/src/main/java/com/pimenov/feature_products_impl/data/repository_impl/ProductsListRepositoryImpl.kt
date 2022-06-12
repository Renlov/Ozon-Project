package com.pimenov.feature_products_impl.data.repository_impl

import com.pimenov.core_network_api.ProductsListApi
import com.pimenov.core_network_api.data_object.ProductInListDTO
import com.pimenov.feature_products_impl.data.mapper.toDO
import com.pimenov.feature_products_impl.domain.repository.ProductsListRepository
import com.pimenov.feature_products_impl.domain_object.ProductInListDO
import com.pimenov.feature_products_impl.presentation.view_object.ProductInListVO
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