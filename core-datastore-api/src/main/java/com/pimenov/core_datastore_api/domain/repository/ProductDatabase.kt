package com.pimenov.core_datastore_api.domain.repository

import com.pimenov.core_datastore_api.data.data_object.ProductPrefs
import com.pimenov.core_datastore_api.data.data_object.ProductInListPrefs

interface ProductDatabase {
    fun addProductInList(list : List<ProductInListPrefs>)
    fun getProductList() : List<ProductInListPrefs>
    fun addProductAdditional(list : List<ProductInListPrefs>)
    fun getProductAdditional() : List<ProductInListPrefs>
    fun addProducts(list : List<ProductPrefs>)
    fun getProducts() : List<ProductPrefs>
    fun getProductByGuid(guid:String): ProductPrefs?
    fun getCountDatabase() : Int
    fun updateCartState(guid:String)
    fun addProductRandom()
    fun getProductListCart() : List<ProductInListPrefs?>
    fun buyAllProductCart()
    fun availablePrice(guid:String, price : Int)
}