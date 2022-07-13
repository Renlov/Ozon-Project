package com.pimenov.feature_pdp_impl.domain.interactor

import android.util.Log
import com.pimenov.feature_pdp_impl.domain.mapper.toVO
import com.pimenov.feature_pdp_impl.domain.repository.PDPRepository
import com.pimenov.feature_pdp_impl.presentation.view_object.ProductVO
import javax.inject.Inject

interface PDPInteractor {
    fun getProductById(guid: String): ProductVO
    fun setInCart(guid: String)
}

class PDPInteractorImpl @Inject constructor(private val repository: PDPRepository): PDPInteractor {
    override fun getProductById(guid: String): ProductVO {
        return repository.getProductById(guid)?.toVO() ?: error("cant find this item")
    }

    override fun setInCart(guid: String) {
        repository.setInCart(guid = guid)
    }
}
