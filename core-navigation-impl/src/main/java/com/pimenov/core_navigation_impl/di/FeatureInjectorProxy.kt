package com.pimenov.core_navigation_impl.di

import android.content.Context
import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_network_impl.di.CoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent
import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent_CoreNetworkDependenciesComponent
import com.pimenov.feature_add_product_impl.di.AddProductFeatureComponent
import com.pimenov.feature_add_product_impl.di.DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent
import com.pimenov.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import com.pimenov.feature_pdp_impl.di.PDPFeatureComponent
import com.pimenov.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import com.pimenov.feature_products_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI() {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productNavigationApi(DaggerCoreNavigationComponent.builder().build().getProductNavigation())
                .build()
        )
    }

    fun initFeaturePDPDI(context: Context) {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .pDPNavigationApi(DaggerCoreNavigationComponent.builder().build().getPDPNavigation())
                .build(),
            context
        )
    }

    fun initFeatureAddDI() {
        AddProductFeatureComponent.initAndGet(
            DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .addProductNavigationApi(DaggerCoreNavigationComponent.builder().build().getAddProductNavigation())
                .build()
        )
    }
}
