package com.pimenov.feature_pdp_impl.presentation.view_custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.pimenov.feature_pdp_impl.databinding.CustomViewPdpBinding

class ButtonInCart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding : CustomViewPdpBinding = CustomViewPdpBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.buttonCart.setOnClickListener {
            it.isGone = true
        }
    }


}
