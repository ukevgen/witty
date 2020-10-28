package com.witty.bank.extension

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl", "placeHolder")
fun ImageView.showImage(image: String?, placeHolderResId: Drawable) {
    Glide.with(context)
        .load(image)
        .placeholder(placeHolderResId)
        .into(this@showImage)
}

@SuppressLint("RestrictedApi")
@BindingAdapter("android:text")
fun setText(view: EditText, oldText: String?, text: String?) {
    TextViewBindingAdapter.setText(view, text)
    if (text == null) return
    if (text == oldText || oldText == null) {
        view.setSelection(text.length)
    }
}