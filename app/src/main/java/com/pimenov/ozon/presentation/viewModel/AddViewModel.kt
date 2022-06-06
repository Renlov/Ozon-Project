package com.pimenov.ozon.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pimenov.ozon.domain.interactors.ProductsInteractorList

class AddViewModel(val productInteractor : ProductsInteractorList) : ViewModel() {

    fun addElement(){
        productInteractor.addProduct()
        Log.d("spectra", productInteractor.getProducts().size.toString())
    }
}