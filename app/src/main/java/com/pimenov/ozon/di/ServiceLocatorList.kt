package com.pimenov.ozon.di

import com.pimenov.ozon.data.repositoriesImpl.MockProductsRepositoryListImpl
import com.pimenov.ozon.domain.interactors.ProductsInteractorList
import com.pimenov.ozon.domain.interactors.ProductsInteractorListImpl

class ServiceLocatorList {
    val productsInteractor: ProductsInteractorList by lazy {
        ProductsInteractorListImpl(MockProductsRepositoryListImpl())
    }
}