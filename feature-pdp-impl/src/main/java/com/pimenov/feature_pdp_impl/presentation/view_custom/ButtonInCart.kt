package com.pimenov.feature_pdp_impl.presentation.view_custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pimenov.feature_pdp_impl.R
import com.pimenov.feature_pdp_impl.databinding.CustomViewPdpBinding

class ButtonInCart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val _binding: CustomViewPdpBinding =
        CustomViewPdpBinding.inflate(LayoutInflater.from(context), this)

    val binding: CustomViewPdpBinding
        get() = _binding

    private var count = 0

    init {
        showButton()
    }

    private fun showButton() {
        count = 0
        with(_binding) {
            buttonCart.visibility = View.VISIBLE
            buttonPlus.visibility = View.GONE
            buttonMinus.visibility = View.GONE
            countProduct.visibility = View.GONE
        }
    }

    fun showCounter() {
        count++
        with(_binding) {
            buttonCart.visibility = View.GONE
            buttonPlus.visibility = View.VISIBLE
            buttonMinus.visibility = View.VISIBLE
            countProduct.visibility = View.VISIBLE
            countProduct.text = count.toString()
        }
    }

    fun isAvailable() {
        binding.buttonCart.setBackgroundColor(resources.getColor(R.color.black, null))
        binding.buttonCart.text = "Закончилось"
        binding.buttonCart.isClickable = false
    }

    fun increaseCount(dataCount : Int) {
        if (dataCount > count){
            count++
            binding.countProduct.text = count.toString()
        }
    }

    fun decreaseCount(dataCount : Int){
        count--
        if (dataCount > count && count != 0){
            binding.countProduct.text = count.toString()
        } else showButton()
    }
}
