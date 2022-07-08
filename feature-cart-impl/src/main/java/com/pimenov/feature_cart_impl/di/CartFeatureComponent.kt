package com.pimenov.feature_cart_impl.di

import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_utils.ViewModelFactory
import com.pimenov.core_utils.di.PerFeature
import com.pimenov.feature_cart_api.CartNavigationApi
import com.pimenov.feature_cart_impl.presentaion.view.CartFragment
import dagger.Component


@Component(
    modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
    dependencies = [CartFeatureDependencies::class])
@PerFeature
abstract class CartFeatureComponent {
    abstract fun fabric() : ViewModelFactory

    companion object {
        @Volatile
        var cartFeatureComponent: CartFeatureComponent? = null
            private set

        fun initAndGet(cartFeatureDependencies: CartFeatureDependencies): CartFeatureComponent? {
            if (cartFeatureComponent == null) {
                synchronized(CartFeatureComponent::class) {
                    cartFeatureComponent = DaggerCartFeatureComponent.builder()
                        .cartFeatureDependencies(cartFeatureDependencies)
                        .build()
                }
            }
            return cartFeatureComponent
        }

        fun get(): CartFeatureComponent? {
            if (cartFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(cartFeatureDependencies: CartFeatureDependencies)' method")
            }
            return cartFeatureComponent
        }

        fun resetComponent() {
            cartFeatureComponent = null
        }
    }

    abstract fun inject(fragment: CartFragment)

    @PerFeature
    @Component(dependencies = [CartNavigationApi::class, DatabaseApi::class])
    interface CartFeatureDependenciesComponent : CartFeatureDependencies
}