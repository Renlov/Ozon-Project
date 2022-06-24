package com.pimenov.core_network_api

interface NetworkApi {
    fun getProductsApi(): WorkerApi
    fun getServiceApi() : ServiceApi
    fun getProductRepository() : ProductRepository
}
