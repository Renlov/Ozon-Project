package com.pimenov.feature_pdp_impl.data.repository_impl


import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import com.pimenov.core_network_api.ProductsApi
import com.pimenov.feature_pdp_impl.data.mapper.toDO
import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO
import com.pimenov.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(private val productDatabase: ProductDatabase) : PDPRepository {
    override fun getProductById(guid: String): ProductDO? {
        return productDatabase.getProductByGuid(guid)?.toDO()
    }
}
