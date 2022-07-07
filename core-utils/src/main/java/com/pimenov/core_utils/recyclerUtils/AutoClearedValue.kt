package com.pimenov.core_utils.recyclerUtils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T : Any>(fragment: Fragment, event: Lifecycle.Event, value: T) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        _value = value
        fragment.lifecycle.addObserver(LifecycleEventObserver { _, _event ->
            if (_event == event)
                _value = null
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException("null value")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

fun <T : Any> Fragment.autoCleared(
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    block: () -> T
): AutoClearedValue<T> {
    return AutoClearedValue(this, event, block())
}
