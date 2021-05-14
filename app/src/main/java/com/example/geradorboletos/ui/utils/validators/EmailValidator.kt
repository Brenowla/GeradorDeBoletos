package com.example.geradorboletos.ui.utils.validators

import android.util.Patterns

class EmailValidator {

    companion object {

        fun isValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

}