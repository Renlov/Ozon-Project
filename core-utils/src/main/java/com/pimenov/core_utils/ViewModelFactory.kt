package com.pimenov.core_utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

typealias ViewModelCreator = () -> ViewModel?

class ViewModelFactory(
    private val viewModelCreator: ViewModelCreator = { null }
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModelCreator() as T
}

inline fun <reified VM : ViewModel> Fragment.viewModelCreator(noinline creator: ViewModelCreator): Lazy<VM> =
    viewModels { ViewModelFactory(creator) }

//class ViewModelFactory @Inject constructor(
//    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return viewModelFactories.getValue(modelClass as Class<ViewModel>).get() as T
//    }
//}
//
//@MustBeDocumented
//@Target(AnnotationTarget.FUNCTION)
//@Retention(AnnotationRetention.RUNTIME)
//@MapKey
//annotation class ViewModelKey(val value: KClass<out ViewModel>)