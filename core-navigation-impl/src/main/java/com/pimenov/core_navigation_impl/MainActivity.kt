package com.pimenov.core_navigation_impl

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_navigation_impl.databinding.ActivityMainBinding
import com.pimenov.core_navigation_impl.di.FeatureInjectorProxy
import com.pimenov.feature_products_impl.presentation.view.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val snackBar: Snackbar by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Snackbar.make(this, binding.root, "Internet not found...", Snackbar.LENGTH_INDEFINITE)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateProduct()
        val connectivityManager: ConnectivityManager =
            getSystemService(ConnectivityManager::class.java)
        toggleSnackbar(connectivityManager.isDefaultNetworkActive)
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                toggleSnackbar(true)
            }
            override fun onLost(network: Network) {
                toggleSnackbar(false)
            }
            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {

            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            }
        })

    }

    private fun navigateProduct() {
        FeatureInjectorProxy.initFeatureProductsDI(applicationContext)
        val newFragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment, MainFragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }
    internal fun toggleSnackbar(isNetworkAvailable: Boolean) {
        runOnUiThread {
            if (isNetworkAvailable) {
                snackBar.dismiss()
            } else snackBar.show()
        }
    }
}