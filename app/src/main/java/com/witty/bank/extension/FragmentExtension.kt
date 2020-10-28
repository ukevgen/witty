package com.witty.bank.extension

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun Fragment.withArguments(vararg pairs: Pair<String, Any?>) =
    this.apply { arguments = bundleOf(*pairs) }