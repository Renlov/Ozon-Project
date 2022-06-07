package com.pimenov.ozon.di

import android.app.Application
import android.content.Context
import com.pimenov.ozon.data.dataStore.CountPrefs
import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryImpl
import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryListImpl
import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.domain.interactors.ProductsInteractorImpl
import com.pimenov.ozon.domain.interactors.ProductsInteractorList
import com.pimenov.ozon.domain.interactors.ProductsInteractorListImpl

object ServiceLocator {

    private lateinit var context: Application

    fun init(context: Application){
        this@ServiceLocator.context = context
    }

    val productsInteractor: ProductsInteractor by lazy {
        ProductsInteractorImpl(MockProductsRepositoryImpl())
    }
    val productsInteractorList: ProductsInteractorList by lazy {
        ProductsInteractorListImpl(MockProductsRepositoryListImpl())
    }
    val countPrefs: CountPrefs by lazy {
        CountPrefs(context)
    }
}