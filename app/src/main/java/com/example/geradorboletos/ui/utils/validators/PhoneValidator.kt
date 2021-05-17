package com.example.geradorboletos.ui.utils.validators

import com.example.geradorboletos.ui.utils.Mask

class PhoneValidator {

    companion object {
        private val FORMAT = "^[1-9]{2}[9]{0,1}[6-9]{1}[0-9]{3}[0-9]{4}\$".toRegex()

        fun isValid(phone: String): Boolean {
            val str = Mask.replaceChars(phone)
            return FORMAT.matches(str)
        }
    }
}