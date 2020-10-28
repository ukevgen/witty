package com.witty.bank.android.system

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceManager @Inject constructor(private val context: ContextHolder) {

    fun getString(@StringRes id: Int): String =
        context.getContext()?.getString(id) ?: ""

    fun getString(@StringRes id: Int, vararg formatArgs: Any) =
        context.getContext()?.getString(id, *formatArgs)?.let { String.format(it) }
            ?: ""

    fun getStringArray(@ArrayRes id: Int): Array<String> {
        return context.getContext()?.resources?.getStringArray(id) ?: arrayOf()
    }

}