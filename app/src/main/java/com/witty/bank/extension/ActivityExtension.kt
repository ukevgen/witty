package com.witty.bank.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    currentFocus?.apply {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Activity.showKeyboard() {
    currentFocus?.apply {
        getSystemService(InputMethodManager::class.java)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}