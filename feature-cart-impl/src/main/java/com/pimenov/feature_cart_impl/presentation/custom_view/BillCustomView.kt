package com.pimenov.feature_cart_impl.presentation.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.pimenov.feature_cart_impl.R



typealias OnActionListener = (isClick : Boolean) -> Unit
class BillCustomView @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyleAttributeSet: Int = 0)
    : View(context, attrs, defStyleAttributeSet) {

    private val sizeText = 50f
    private val strokeText = 16f
    private val corner = 15f
    private val defaultValue = 0f
    private var location = 0
    var textValueSum : String = ""
    var actionListener : OnActionListener? = null


    private val buttonTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = sizeText
        style = Paint.Style.FILL
        strokeWidth = strokeText
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = sizeText
        style = Paint.Style.FILL
        strokeWidth = strokeText
        color = ContextCompat.getColor(context, R.color.black)
    }

    private val buttonPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var rect: RectF = RectF(defaultValue, defaultValue, defaultValue, defaultValue)


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawButton(canvas)
        drawText(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when(event.action){
            MotionEvent.ACTION_DOWN ->{
                if (event.x.toInt() > location){
                    actionListener?.invoke(true)
                }
                true
            }
            else ->{
                false
            }
        }
    }

    private fun drawButton(canvas: Canvas){
        val text = resources.getString(R.string.text_on_button)
        val textWidth = buttonTextPaint.measureText(text)
        rect.set(canvas.width.toFloat() / 2, defaultValue, canvas.width.toFloat(), canvas.height.toFloat())
        canvas.drawRoundRect(rect, corner, corner, buttonPaint)
        canvas.drawText(text,
            (canvas.width.toFloat() / 2) + (canvas.width.toFloat() / 4) - textWidth / 2,
            (canvas.height.toFloat() / 2 ) - (buttonTextPaint.descent() + buttonTextPaint.ascent()) / 2,
            buttonTextPaint)
    }

    private fun drawText(canvas: Canvas){
        val text = resources.getString(R.string.text_sum, textValueSum)
        val textWidth = textPaint.measureText(text)
        canvas.drawText(text,
            (canvas.width.toFloat() / 2 - textWidth) / 2,
            (canvas.height.toFloat() / 2 ) - (textPaint.descent() + textPaint.ascent()) / 2,
            textPaint)
        invalidate()
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
        location = w / 2
        buttonPaint.shader = LinearGradient(
            defaultValue,
            h.toFloat(),
            defaultValue,
            defaultValue,
            ContextCompat.getColor(context, R.color.ozon) ,
            ContextCompat.getColor(context, R.color.ozon_end),
            Shader.TileMode.CLAMP)
    }
}