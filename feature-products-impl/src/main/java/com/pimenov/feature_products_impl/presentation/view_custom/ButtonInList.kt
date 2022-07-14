package com.pimenov.feature_products_impl.presentation.view_custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.pimenov.feature_products_impl.R
import com.pimenov.feature_products_impl.databinding.CustomViewButtonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ButtonInList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _binding: CustomViewButtonBinding =
        CustomViewButtonBinding.inflate(LayoutInflater.from(context), this)

    val binding: CustomViewButtonBinding
        get() = _binding

    val buttonState: MutableStateFlow<ButtonState> = MutableStateFlow(
        ButtonState(isInCart = false, isLoading = true)
    )

    init {
        CoroutineScope(Dispatchers.Main).launch {
            buttonState.collect {
                renderViewState(it)
            }
        }
    }

    private enum class State(val text: Int, val color: Int) {
        IN_CART_OFF(R.string.inCartOff, R.color.ozonColor),
        IN_CART_ON(R.string.inCartOn, R.color.ozonColorOn)
    }

    private fun renderViewState(buttonState: ButtonState) {
        with(_binding) {
            when (buttonState) {
                ButtonState(isInCart = true, isLoading = false) -> {
                    btnCartOn.isVisible = true
                    progressBarState.isVisible = false
                    btnCartOn.setBackgroundColor(
                        resources.getColor(
                            State.IN_CART_ON.color,
                            null
                        )
                    )
                    btnCartOn.text = resources.getString(State.IN_CART_ON.text)
                }
                ButtonState(isInCart = false, isLoading = false) -> {
                    btnCartOn.isVisible = true
                    progressBarState.isVisible = false
                    btnCartOn.setBackgroundColor(resources
                        .getColor(State.IN_CART_OFF.color, null)
                    )
                    btnCartOn.text = resources.getString(State.IN_CART_OFF.text)
                }
                else -> {
                    btnCartOn.isVisible = false
                    progressBarState.isVisible = true
                }
            }
        }
    }

    data class ButtonState(
        val isInCart: Boolean,
        val isLoading: Boolean
    )
}