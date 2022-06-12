package com.pimenov.core_navigation_impl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_products_impl.presentation.view.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateProduct()
    }

    private fun navigateProduct() {
        FeatureInjectorProxy.initFeatureProductsDI()
        val newFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, MainFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }
}