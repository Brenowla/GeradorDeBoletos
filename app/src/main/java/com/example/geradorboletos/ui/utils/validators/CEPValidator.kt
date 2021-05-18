package com.example.geradorboletos.ui.utils.validators

import com.example.geradorboletos.ui.utils.Mask

class CEPValidator {
    companion object {
        private val CEP = "^[0-9]{8}\$".toRegex()
        fun isValid(cep: String): Boolean{
            val str = Mask.replaceChars(cep)
            return CEP.matches(str)
        }
    }
}