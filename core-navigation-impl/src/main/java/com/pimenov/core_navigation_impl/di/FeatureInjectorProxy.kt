package com.pimenov.core_navigation_impl.di

import android.content.Context
import androidx.work.WorkManager
import com.pimenov.core_datastore_impl.di.CoreDatabaseComponent
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.pimenov.feature_add_product_impl.di.AddProductFeatureComponent
import com.pimenov.feature_add_product_impl.di.DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent
import com.pimenov.feature_cart_impl.di.CartFeatureComponent
import com.pimenov.feature_cart_impl.di.DaggerCartFeatureComponent_CartFeatureDependenciesComponent
import com.pimenov.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import com.pimenov.feature_pdp_impl.di.PDPFeatureComponent
import com.pimenov.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import com.pimenov.feature_products_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {

        fun initFeatureProductsDI(context: Context) {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder().databaseApi(CoreDatabaseComponent.initAndGet(context)).build(),
                    WorkManager.getInstance(context)))
                .productNavigationApi(DaggerCoreNavigationComponent.builder().build().getProductNavigation())
                .databaseApi(CoreDatabaseComponent.initAndGet(context))
                .build()
        )
    }


    fun initFeatureCartDI(context: Context) {
        CartFeatureComponent.initAndGet(
            DaggerCartFeatureComponent_CartFeatureDependenciesComponent.builder()
                .databaseApi(CoreDatabaseComponent.initAndGet(context))
                .cartNavigationApi(DaggerCoreNavigationComponent.builder().build().getCartNavigation())
                .build()
        )
    }


    fun initFeaturePDPDI(context: Context) {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder().databaseApi(CoreDatabaseComponent.initAndGet(context)).build(),
                    WorkManager.getInstance(context)))
                .pDPNavigationApi(DaggerCoreNavigationComponent.builder().build().getPDPNavigation())
                .databaseApi(CoreDatabaseComponent.initAndGet(context))
                .build(),
            context
        )
    }

    fun initFeatureAddDI(context: Context) {
        AddProductFeatureComponent.initAndGet(
            DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.initAndGet(DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent.builder().databaseApi(CoreDatabaseComponent.initAndGet(context)).build(),
                    WorkManager.getInstance(context)))
                .addProductNavigationApi(DaggerCoreNavigationComponent.builder().build().getAddProductNavigation())
                .databaseApi(CoreDatabaseComponent.initAndGet(context))
                .build()
        )
    }
}
