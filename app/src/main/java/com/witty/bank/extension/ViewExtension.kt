package com.witty.bank.extension

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.core.graphics.drawable.DrawableCompat

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


fun View.setBackgroundTintByColor(@ColorInt color: Int) {
    val wrappedDrawable = DrawableCompat.wrap(background)
    DrawableCompat.setTint(wrappedDrawable.mutate(), color)
}

fun TextView.setDrawable(start: Boolean, drawable: Drawable?) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(
        if (start) drawable else null,
        null,
        if (!start) drawable else null,
        null
    )
}

