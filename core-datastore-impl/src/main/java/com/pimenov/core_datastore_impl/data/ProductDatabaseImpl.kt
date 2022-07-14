package com.pimenov.core_datastore_impl.data

import android.content.Context
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
        sharedPreferences.edit().putString(PREFERENCE_PRODUCT_LIST, Gson().toJson(list))
            .apply()
    }

    override fun getProductList(): List<ProductInListPrefs> {
        return sharedPreferences.getString(PREFERENCE_PRODUCT_LIST, null)?.let { json ->
            GsonBuilder().create().fromJson(json, Array<ProductInListPrefs>::class.java).toMutableList()
        } ?: emptyList()
    }

    override fun addProductAdditional(list: List<ProductInListPrefs>) {
        sharedPreferences.edit().putString(PREFERENCE_ADDITIONAL, Gson().toJson((getProductAdditional() + list).toSet())).apply()
    }

    override fun getProductAdditional(): List<ProductInListPrefs> {
        return sharedPreferences.getString(PREFERENCE_ADDITIONAL, null)?.let { json ->
            GsonBuilder().create().fromJson(json, Array<ProductInListPrefs>::class.java).toMutableList()
        }?: emptyList()
    }

    override fun addProducts(list: List<ProductPrefs>) {
        sharedPreferences.edit().putString(PREFERENCE_PRODUCTS, Gson().toJson(list))
            .apply()
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

    override fun updateCartState(guid:String) {
        addProducts(getProducts().toMutableList().map {
            if (it.guid == guid) it.copy(isInCart = !it.isInCart) else it
        })
        
        addProductInList(getProductList().map {
            if (it.guid == guid)it.copy(isInCart = !it.isInCart) else it
        })
        if (getProductAdditional().isEmpty()) return
         (getProductAdditional().map {
            if (it.guid == guid) it.copy(isInCart = !it.isInCart) else it
        })
    }

    override fun availablePrice(guid:String, price : Int){
        addProductInList(getProductList().map {
            if (it.guid == guid)it.copy(price = price.toString()) else it
        })
    }

    override fun addProductRandom() {
        val productInList = getProductList().toMutableList()
        val productList = getProducts().toMutableList()
        val productAdditional = getProductAdditional().toMutableList()

        val product = productInList.random()
        val newGuid = UUID.randomUUID().toString()

        productList.add(productList.find { it.guid == product.guid }?.copy(guid = newGuid, isInCart = false) ?: error("cant create new product"))
        productInList.add(product.copy(guid = newGuid, isInCart = false))
        productAdditional.add(product.copy(guid = newGuid, isInCart = false))

        addProductInList(productInList)
        addProducts(productList)
        addProductAdditional(productAdditional)
    }

    override fun getProductListCart(): List<ProductInListPrefs?> {
        return getProductList().filter {
            it.isInCart
        }
    }

    override fun buyAllProductCart() {
        addProducts(getProducts().toMutableList().map {
            it.copy(isInCart = false)
        })
        addProductInList(getProductList().map {
            it.copy(isInCart = false)
        })
        if (getProductAdditional().isEmpty()) return
        addProductAdditional(getProductAdditional().map {
            it.copy(isInCart = false)
        })
    }

    companion object {
        private const val PREFERENCE = "Preference"
        private const val PREFERENCE_PRODUCT_LIST = "ProductInList"
        private const val PREFERENCE_PRODUCTS = "Products"
        private const val PREFERENCE_ADDITIONAL = "AdditionalList"
    }
}