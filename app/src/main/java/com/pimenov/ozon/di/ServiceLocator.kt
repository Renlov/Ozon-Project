package com.pimenov.ozon.di

import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryImpl

import com.pimenov.ozon.domain.interactors.ProductsInteractor
import com.pimenov.ozon.domain.interactors.ProductsInteractorImpl

class ServiceLocator {
    val productsInteractor: ProductsInteractor by lazy {
        ProductsInteractorImpl(MockProductsRepositoryImpl())
    }
}