package ru.dmitriyt.lesson11

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import ru.dmitriyt.lesson11.databinding.ViewTwoLinesBinding

class TwoLinesTextView : LinearLayout {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private val binding = ViewTwoLinesBinding.inflate(LayoutInflater.from(context), this)

    private fun init(context: Context, attrs: AttributeSet?) {
        orientation = VERTICAL

        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TwoLinesTextView,
            0,
            0
        )

        try {
            setTitle(a.getString(R.styleable.TwoLinesTextView_title).orEmpty())
            setMessage(a.getString(R.styleable.TwoLinesTextView_message).orEmpty())
        } finally {
            a.recycle()
        }
    }

    fun setTitle(title: String) {
        binding.textViewTitle.text = title
    }

    fun setMessage(message: String) {
        binding.textViewMessage.text = message
    }
}