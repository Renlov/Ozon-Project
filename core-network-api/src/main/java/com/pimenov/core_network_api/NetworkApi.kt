package com.pimenov.core_network_api

interface NetworkApi {
    fun getProductsApi(): WorkerManagerProduct
    fun getServiceApi() : ServiceApi
    fun getProductRepository() : ProductRepository
}
