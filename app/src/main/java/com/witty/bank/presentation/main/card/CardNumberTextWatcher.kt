package com.witty.bank.presentation.main.card

import android.text.Editable
import android.text.TextWatcher


class CardNumberTextWatcher : TextWatcher {

    companion object {
        private const val DIGITS_PATTER = "0123456789"
        private const val SPACE = ' '
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        var pos = 0
        while (true) {
            if (pos >= s.length) break
            if (SPACE == s[pos] && ((pos + 1) % 5 != 0 || pos + 1 == s.length)) {
                s.delete(pos, pos + 1)
            } else {
                pos++
            }
        }

        pos = 4
        while (true) {
            if (pos >= s.length) break
            val c = s[pos]
            if (DIGITS_PATTER.indexOf(c) >= 0) {
                s.insert(pos, "" + SPACE)
            }
            pos += 5
        }
    }
}