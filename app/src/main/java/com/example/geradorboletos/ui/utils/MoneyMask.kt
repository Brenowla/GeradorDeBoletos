package com.example.geradorboletos.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat

class MoneyMask {

    companion object {

        fun replaceCharsReal(field: String): String {
            return field.replace("[R$,.]".toRegex(), "")
        }

        fun mask(type: String ,text: EditText): TextWatcher {
            val textWatcher: TextWatcher = object : TextWatcher {
                var current = ""
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.toString() != current) {
                        text.removeTextChangedListener(this)
                        val cleanString = s.toString().replace("[${type},.]".toRegex(), "")
                        val parsed = cleanString.toDouble()
                        val formatted: String =
                            NumberFormat.getCurrencyInstance().format(parsed / 100)
                        current = type+formatted.replace("[${type}]".toRegex(), "")
                        text.setText(current)
                        text.setSelection(current.length)
                        text.addTextChangedListener(this)
                    }
                }

            }
            return textWatcher
        }

        fun imediateMask(type: String ,text: String): String {
            val parsed = text.toDouble()
            val formatted: String =
                NumberFormat.getCurrencyInstance().format(parsed / 100)
            return type+formatted.replace("[${type}]".toRegex(), "")
        }

    }

}