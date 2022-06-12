package com.pimenov.feature_pdp_impl.data.repository_impl


import com.pimenov.core_network_api.ProductApi
import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.feature_pdp_impl.data.mapper.toDO
import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO
import com.pimenov.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(private val productsApi: ProductApi) : PDPRepository {
    override fun getProductById(guid: String): ProductDO? {
        return productsApi.getProductById(guid)?.toDO()
    }
}
