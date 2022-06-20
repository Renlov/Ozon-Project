package com.pimenov.core_datastore_impl.data

import android.content.Context
import com.google.gson.Gson
import com.pimenov.core_datastore_api.data.data_object.ProductDTO
import com.pimenov.core_datastore_api.data.data_object.ProductInListDTO
import com.pimenov.core_datastore_api.domain.repository.ProductDatabase
import javax.inject.Inject


class ProductDatabaseImpl@Inject constructor(context: Context) : ProductDatabase {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit()

    override fun getProductList(): List<ProductInListDTO> {
        return emptyList()

        //return sharedPreference(PREFERENCE_PRODUCT_LIST, list)
    }

    override fun getProducts(): List<ProductDTO> {
        return emptyList()
    }

    override fun addProduct() {

    }

    companion object {
        private const val PREFERENCE = "Preference"
        private const val PREFERENCE_PRODUCT_LIST = "ProductInList"
        private const val PREFERENCE_PRODUCTS = "Products"
    }
}