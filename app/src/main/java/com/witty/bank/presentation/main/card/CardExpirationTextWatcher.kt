package com.witty.bank.presentation.main.card

import android.text.Editable
import android.text.TextWatcher


class CardExpirationTextWatcher : TextWatcher {

    companion object {
        const val SLASH = '/'
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        // Remove all spacing char
        var pos = 0
        while (true) {
            if (pos >= s.length) break
            if (SLASH == s[pos] && ((pos + 1) % 3 != 0 || pos + 1 == s.length)) {
                s.delete(pos, pos + 1)
            } else {
                pos++
            }
        }

        // Insert char where needed.
        pos = 2
        while (true) {
            if (pos >= s.length) break
            val c = s[pos]
            // Only if its a digit where there should be a space we insert a space
            if ("0123456789".indexOf(c) >= 0) {
                s.insert(pos, "" + SLASH)
            }
            pos += 3
        }
    }

}