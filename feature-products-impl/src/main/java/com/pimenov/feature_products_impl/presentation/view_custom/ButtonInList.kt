package com.pimenov.feature_products_impl.presentation.view_custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.CustomViewButtonBinding

class ButtonInList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _binding: CustomViewButtonBinding =
        CustomViewButtonBinding.inflate(LayoutInflater.from(context), this)

    val binding: CustomViewButtonBinding
        get() = _binding

    init {
        renderViewState(false, false)
    }


    private enum class ButtonState(val text: Int, val color: Int) {
        IN_CART_OFF(R.string.inCartOff, R.color.ozonColor),
        IN_CART_ON(R.string.inCartOn, R.color.ozonColorOn)
    }

    fun renderViewState(isInCart: Boolean, isLoading: Boolean) {
        with(_binding) {
            if (isInCart) {
                btnCartOn.isVisible = true
                progressBarState.isVisible = false
                btnCartOn.setBackgroundColor(
                    resources.getColor(
                        ButtonState.IN_CART_ON.color,
                        null
                    )
                )
                btnCartOn.text = resources.getString(ButtonState.IN_CART_ON.text)
            } else if (isLoading) {
                btnCartOn.isVisible = false
                progressBarState.isVisible = true
            } else {
                btnCartOn.isVisible = true
                progressBarState.isVisible = false
                btnCartOn.setBackgroundColor(
                    resources.getColor(
                        ButtonState.IN_CART_OFF.color,
                        null
                    )
                )
                btnCartOn.text = resources.getString(ButtonState.IN_CART_OFF.text)
            }
        }
    }
}

