package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import retrofit2.http.GET

interface ServiceApi {
    @GET("50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
    suspend fun getListProducts() : List<ProductInListDTO>

    @GET("8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
    suspend fun getProducts() : List<ProductDTO>
}