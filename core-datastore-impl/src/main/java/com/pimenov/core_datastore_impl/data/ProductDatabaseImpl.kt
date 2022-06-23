package com.pimenov.core_datastore_impl.data

import android.content.Context
import android.util.Log
import android.util.Log.i
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pimenov.core_datastore_api.data.data_object.ProductPrefs
import com.pimenov.core_datastore_api.data.data_object.ProductInListPrefs
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import java.util.*
import javax.inject.Inject


class ProductDatabaseImpl@Inject constructor(context: Context) : ProductDatabase {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

    override fun addProductInList(list: List<ProductInListPrefs>) {
        sharedPreferences.edit().putString(PREFERENCE_PRODUCT_LIST, Gson().toJson((getProductList() + list).toSet()))
            .apply()
    }

    override fun getProductList(): List<ProductInListPrefs> {
        return sharedPreferences.getString(PREFERENCE_PRODUCT_LIST, null)?.let { json ->
            GsonBuilder().create().fromJson(json, Array<ProductInListPrefs>::class.java).toMutableList()
        } ?: emptyList()
    }

    override fun addProducts(list: List<ProductPrefs>) {
        sharedPreferences.edit().putString(PREFERENCE_PRODUCTS, Gson().toJson((getProducts() + list).toSet())).apply()
    }

    override fun getProducts(): List<ProductPrefs> {
        return sharedPreferences.getString(PREFERENCE_PRODUCTS, null)?.let { json ->
            GsonBuilder().create().fromJson(json, Array<ProductPrefs>::class.java).toMutableList()
        } ?: emptyList()
    }

    override fun getProductByGuid(guid: String): ProductPrefs? {
        return getProducts().find { it.guid == guid }
    }

    override fun getCountDatabase(): Int {
        return getProductList().size
    }

    override fun addProductRandom() {
        val productInList = getProductList().toMutableList()
        val productList = getProducts().toMutableList()

        val product = productInList.random()
        val newGuid = UUID.randomUUID().toString()

        productList.add(productList.find { it.guid == product.guid }?.copy(guid = newGuid) ?: error("cant create new product"))
        productInList.add(product.copy(guid = newGuid))

        addProductInList(productInList)
        addProducts(productList)
    }

    companion object {
        private const val PREFERENCE = "Preference"
        private const val PREFERENCE_PRODUCT_LIST = "ProductInList"
        private const val PREFERENCE_PRODUCTS = "Products"
    }
}