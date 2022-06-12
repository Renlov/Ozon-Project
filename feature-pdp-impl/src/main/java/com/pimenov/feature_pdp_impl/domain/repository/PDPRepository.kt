package com.pimenov.feature_pdp_impl.domain.repository

import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO

interface PDPRepository {
    fun getProductById(guid: String): ProductDO?
}
