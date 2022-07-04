package com.pimenov.feature_products_impl.presentation.view_custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.CustomViewButtonBinding

class ButtonInList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by viewBinding(CustomViewButtonBinding::bind)

    init {

    }

    private enum class ButtonState(val text : Int, val color : Int){
        IN_CART_OFF(R.string.inCartOff, R.color.ozonColor),
        IN_CART_ON(R.string.inCartOn, R.color.ozonColorOn)
    }

}