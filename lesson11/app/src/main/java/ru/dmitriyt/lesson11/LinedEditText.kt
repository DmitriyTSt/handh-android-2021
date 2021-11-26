package ru.dmitriyt.lesson11

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class LinedEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private val rect = Rect()
    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        color = 0x800000
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        background = null
    }

    override fun onDraw(canvas: Canvas) {
        val count = lineCount

        repeat(count) {
            val baseline = getLineBounds(it, rect)
            canvas.drawLine(rect.left.toFloat(), (baseline + 1).toFloat(), rect.right.toFloat(), (baseline + 1).toFloat(), paint)
        }

        super.onDraw(canvas)
    }
}