package com.anik.viewpager2example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.anik.viewpager2example.R

class DottedCircle : View {

    private lateinit var outerRect: RectF
    private lateinit var innerRect: RectF
    //private var angle: Float = 0.toFloat()
    private var innerCircleColor: Int = Color.WHITE
    private var outerCircleColor: Int = Color.BLACK

    private var calculatedHeight = 0
    private var calculatedWidth = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {attributes->
            var typedArray = context.obtainStyledAttributes(attributes, R.styleable.DottedCircle)
            outerCircleColor = typedArray.getColor(R.styleable.DottedCircle_dOuterCircleColor, outerCircleColor)
            innerCircleColor = typedArray.getColor(R.styleable.DottedCircle_dInnerCircleColor, innerCircleColor)
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var halfWidth = (calculatedWidth/2).toFloat()
        var halfHeight = (calculatedHeight/2).toFloat()

        innerRect = RectF(7f, 7f, calculatedWidth.toFloat()-halfWidth , calculatedHeight.toFloat()-halfHeight)
        outerRect = RectF(0f, 0f, calculatedWidth.toFloat(), calculatedHeight.toFloat())

        var paint = Paint()
        paint.isAntiAlias = true

        drawOuterCircle(canvas, paint)
        drawInnerCircle(canvas, paint, halfWidth, halfHeight)
    }

    private fun drawInnerCircle(canvas: Canvas, paint: Paint, centerX: Float, centerY: Float) {
        paint.style = Paint.Style.FILL
        paint.color = innerCircleColor

        canvas.drawCircle(centerX,centerY, centerX/2, paint)
        //canvas.drawArc(innerRect, 0f, 360f, true, paint)
    }

    private fun drawOuterCircle(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.color = outerCircleColor
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(outerRect, 0f, 360f, false, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        calculatedWidth = measureDimension(desiredWidth, widthMeasureSpec)
        calculatedHeight = measureDimension(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(calculatedWidth, calculatedHeight)
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    fun setOuterCircleColor(color: Int) {
        outerCircleColor = color
        invalidate()
    }

    fun setInnerCircleColor(color: Int) {
        innerCircleColor = color
        invalidate()
    }

}