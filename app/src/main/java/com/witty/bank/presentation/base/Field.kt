package com.witty.bank.presentation.base

class Field<T>(initialValue: T, var editable: Boolean, val valueListener: (T) -> Unit) {
    var value: T = initialValue
        set(newValue) {
            field = newValue
            valueListener(newValue)
        }
}