package com.pimenov.feature_add_product_impl.di

import com.pimenov.core_datastore_api.domain.repository.DatabaseApi
import com.pimenov.core_network_api.NetworkApi
import com.pimenov.core_utils.ViewModelFactory
import com.pimenov.core_utils.di.PerFeature
import com.pimenov.feature_add_product_api.AddProductNavigationApi
import com.pimenov.feature_add_product_impl.presentation.view.AddFragment
import dagger.Component

@Component(
    modules = [InteractorModule::class, RepositoryModule::class, ViewModelModule::class],
    dependencies = [AddProductFeatureDependencies::class]
)
@PerFeature
abstract class AddProductFeatureComponent {

    abstract fun fabric() : ViewModelFactory

    companion object {

        @Volatile
        var productFeatureComponent: AddProductFeatureComponent? = null
            private set

        fun initAndGet(productFeatureDependencies: AddProductFeatureDependencies): AddProductFeatureComponent? {
            if (productFeatureComponent == null) {
                synchronized(AddProductFeatureComponent::class) {
                    productFeatureComponent = DaggerAddProductFeatureComponent.builder()
                        .addProductFeatureDependencies(productFeatureDependencies)
                        .build()
                }
            }
            return productFeatureComponent
        }

        fun get(): AddProductFeatureComponent? {
            if (productFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return productFeatureComponent
        }

        fun resetComponent() {
            productFeatureComponent = null
        }

    }

    abstract fun inject(fragment: AddFragment)

    @PerFeature
    @Component(dependencies = [NetworkApi::class, AddProductNavigationApi::class, DatabaseApi::class])
    interface AddProductFeatureDependenciesComponent : AddProductFeatureDependencies
}