package com.pimenov.feature_pdp_impl.presentation.view_custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pimenov.feature_pdp_impl.databinding.CustomViewPdpBinding

class ButtonInCart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CustomViewPdpBinding =
        CustomViewPdpBinding.inflate(LayoutInflater.from(context), this)

    init {
        getButton()
    }



    fun getButton() {
        with(binding) {
            buttonCart.visibility = View.VISIBLE
            buttonPlus.visibility = View.GONE
            buttonMinus.visibility = View.GONE
            countProduct.visibility = View.GONE
        }
    }

    fun getCounter() {
        with(binding) {
            buttonCart.visibility = View.GONE
            buttonPlus.visibility = View.VISIBLE
            buttonMinus.visibility = View.VISIBLE
            countProduct.visibility = View.VISIBLE
        }
    }
}
