package com.kimboo.mvvmkotlin.ui.views

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.widget.TextView
import com.kimboo.mvvmkotlin.R


/**
 * Created by Agustin Tomas Larghi on 5/6/2018.
 * Email: agustin.tomas.larghi@gmail.com
 */
class CompatTintTextView : TextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        getAttributes(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttributes(attrs, context)
    }

    private fun getAttributes(attrs: AttributeSet?, context: Context) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                    R.styleable.CompatTintTextView, 0, 0)
            val color = typedArray.getColor(R.styleable.CompatTintTextView_tintColor, -1)
            setTextViewDrawableColor(color)

            typedArray.recycle()
        }
    }

    private fun setTextViewDrawableColor(@ColorInt color: Int) {
        for (drawable in compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            }
        }
    }

}