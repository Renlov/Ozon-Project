package com.pimenov.feature_pdp_impl.domain.interactor

import com.pimenov.feature_pdp_impl.data.mapper.toDO
import com.pimenov.feature_pdp_impl.domain.domain_object.ProductDO
import com.pimenov.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

interface PDPInteractor {
    fun getProductById(guid: String): ProductDO
}

class PDPInteractorImpl @Inject constructor(private val repository: PDPRepository):
    PDPInteractor {

    override fun getProductById(guid: String): ProductDO {
        return repository.getProductById(guid)?.toDO() ?: error("cant find this item")
    }
}
