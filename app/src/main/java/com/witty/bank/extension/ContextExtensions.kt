package com.witty.bank.extension


import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun Context.getTintDrawable(drawableRes: Int, colorRes: Int): Drawable {
    val source = ContextCompat.getDrawable(this, drawableRes)!!.mutate()
    val wrapped = DrawableCompat.wrap(source)
    DrawableCompat.setTint(wrapped, color(colorRes))
    return wrapped
}

fun Context.getTintDrawable(
    drawableRes: Int,
    colorResources: IntArray,
    states: Array<IntArray>
): Drawable {
    val source = ContextCompat.getDrawable(this, drawableRes)!!.mutate()
    val wrapped = DrawableCompat.wrap(source)
    DrawableCompat.setTintList(
        wrapped,
        ColorStateList(states, colorResources.map { color(it) }.toIntArray())
    )
    return wrapped
}

fun Context.screenDimension(): Point {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun Context.screenWidth(): Int = screenDimension().x
fun Context.screenHeight(): Int = screenDimension().y






