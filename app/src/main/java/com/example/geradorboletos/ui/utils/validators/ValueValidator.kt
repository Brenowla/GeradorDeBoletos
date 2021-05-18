package com.example.geradorboletos.ui.utils.validators

import com.example.geradorboletos.ui.utils.MoneyMask

class ValueValidator {
    companion object {
        fun isValid(value: String) : Boolean {
            val str = MoneyMask.replaceCharsReal(value)
            return str.toInt() > 0
        }
    }
}