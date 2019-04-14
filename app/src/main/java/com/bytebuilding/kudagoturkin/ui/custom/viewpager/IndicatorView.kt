package com.bytebuilding.kudagoturkin.ui.custom.viewpager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.bytebuilding.kudagoturkin.R


class IndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundCircleColor = Color.WHITE

    var selectIndicator: Boolean = false
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cX = (width / 2).toFloat()
        val cY = (height / 2).toFloat()
        val backgroundCircleRadius = (width / 2).toFloat()

        paint.color = backgroundCircleColor
        canvas.drawCircle(
            cX,
            cY,
            backgroundCircleRadius,
            paint
        )

        if (selectIndicator) {
            val backgroundSelectedCircleRadius = (width / 4).toFloat()
            paint.color = ContextCompat.getColor(context, R.color.colorAccent)
            canvas.drawCircle(
                cX,
                cY,
                backgroundSelectedCircleRadius,
                paint
            )
        }
    }

}