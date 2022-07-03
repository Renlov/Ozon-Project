package com.pimenov.feature_products_impl.presentation.view_custom

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.pimenov.feature_products_impl.R


class ButtonInList @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : AppCompatButton(context, attrs, defStyleAttr) {

    private var isTouch = false
    private val rect = RectF()
    private val mPaint: Paint = Paint()
    private val textPaint: Paint = Paint()

    private enum class CartState(val state: Int, val color : Int){
        CART_ON(R.string.inCartOn, R.color.ozonColorOn),
        CART_OFF(R.string.inCartOff, R.color.ozonColor)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isTouch) drawOnRect(canvas) else drawOffRect(canvas)
    }

    private fun drawOffRect(canvas : Canvas){
        mPaint.color = resources.getColor(CartState.CART_OFF.color, null)
        textPaint.color = Color.WHITE
        textPaint.textSize = 30f
        textPaint.style = Paint.Style.FILL

        val length: Float = (width / 2).toFloat()
        val centerTextY = (height / 2).toFloat()

        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, 10f, 10f, mPaint)

        drawCenterText(canvas, length, centerTextY, resources.getString(CartState.CART_OFF.state))
    }

    private fun drawOnRect(canvas: Canvas){
        mPaint.color = resources.getColor(CartState.CART_ON.color, null)
        textPaint.color = Color.WHITE
        textPaint.textSize = 30f
        textPaint.style = Paint.Style.FILL

        val length: Float = (width / 2).toFloat()
        val centerTextY = (height / 2).toFloat()

        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, 10f, 10f, mPaint)

        drawCenterText(canvas, length, centerTextY, resources.getString(CartState.CART_ON.state))
    }

    private fun drawCenterText(canvas: Canvas, x: Float, y: Float, text : String) {
        val centralTextBounds = Rect()
        textPaint.getTextBounds(text, 0, text.length, centralTextBounds)
        val centralTextHeight = centralTextBounds.height()
        val centralTextWidth = centralTextBounds.width()
        val x1 = x - centralTextWidth / 2
        val y1 = y + centralTextHeight / 2
        canvas.drawText(text, x1, y1, textPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isTouch = true
                this.invalidate()
                return true
            }
            MotionEvent.ACTION_UP-> {
                isTouch = true
                this.invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}