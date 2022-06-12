package com.pimenov.core_navigation_impl.di

import com.pimenov.core_network_impl.di.DaggerCoreNetworkComponent
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

//    fun initFeaturePDPDI() {
//        PDPFeatureComponent.initAndGet(
//            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
//                .networkApi(DaggerCoreNetworkComponent.builder().build())
//                .pDPNavigationApi(DaggerCoreNavigationComponent.builder().build().getPDPNavigation())
//                .build()
//        )
//    }

}
