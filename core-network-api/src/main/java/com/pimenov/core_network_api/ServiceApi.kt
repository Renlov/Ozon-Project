package com.pimenov.core_network_api

import com.pimenov.core_network_api.data_object.ProductDTO
import com.pimenov.core_network_api.data_object.ProductInListDTO
import retrofit2.http.GET

interface ServiceApi {
    @GET("ee6876a1-8c02-45aa-bde4-b91817a8b210")
    suspend fun getListProducts() : List<ProductInListDTO>

    @GET("d1b4763b-a5ea-471f-83bf-796da466e3d8")
    suspend fun getProducts() : List<ProductDTO>
}