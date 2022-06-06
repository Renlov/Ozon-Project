package com.pimenov.ozon.di

import android.content.Context
import com.pimenov.ozon.data.dataStore.CountPrefs
import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryImpl
import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryListImpl
import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.domain.interactors.ProductsInteractorImpl
import com.pimenov.ozon.domain.interactors.ProductsInteractorList
import com.pimenov.ozon.domain.interactors.ProductsInteractorListImpl

class ServiceLocator(private val context : Context) {
    val productsInteractor: ProductsInteractor by lazy {
        ProductsInteractorImpl(MockProductsRepositoryImpl())
    }
    val productsInteractorList: ProductsInteractorList by lazy {
        ProductsInteractorListImpl(MockProductsRepositoryListImpl())
    }
    val countPrefs : CountPrefs = CountPrefs(context)
}