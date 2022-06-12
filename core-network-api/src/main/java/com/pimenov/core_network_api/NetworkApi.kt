package com.pimenov.core_network_api

interface NetworkApi {
    fun getProductListApi(): ProductsListApi
    fun getProductApi() : ProductApi
}
