package com.pimenov.feature_cart_impl.presentaion.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.minus
import com.pimenov.feature_cart_impl.R


class BillCustomView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyleAttributeSet: Int = 0)
    : View(context, attrs, defStyleAttributeSet) {

    private val buttonText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 14F
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val button = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.ozon)
    }

    private val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawButton(canvas)
    }

    private fun drawButton(canvas: Canvas){
        canvas.drawRoundRect(RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat()), 15f, 15f, button)
    }

    //Пытаемся договориться с компоновщиком, чтобы он дал компоненту определенную высоту и ширину
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight  = suggestedMinimumHeight + paddingTop + paddingBottom
        setMeasuredDimension(resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec))
    }

    //Уже имеем размеры и можем переопределить их
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

}