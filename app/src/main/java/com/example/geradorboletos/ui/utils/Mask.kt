package com.example.geradorboletos.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class Mask {

    companion object {
        fun replaceChars(field: String): String {
            return field.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }

        fun mask(mask: String, editText: EditText): TextWatcher {

            val textWatcher: TextWatcher = object : TextWatcher {
                var isUpdating: Boolean = false
                var oldString: String = ""
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str = replaceChars(s.toString())
                    var fieldWithMask = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating) {
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m: Char in mask.toCharArray()) {
                        if (m != '#' && str.length >= oldString.length) {
                            fieldWithMask += m
                            continue
                        }
                        try {
                            fieldWithMask += str.get(i)
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    editText.setText(fieldWithMask)
                    editText.setSelection(fieldWithMask.length)

                }

                override fun afterTextChanged(editable: Editable) {

                }
            }

            return textWatcher
        }

        fun imediateMask(mask:String, str: String): String {
            var fieldWithMask = ""
            var i = 0
            for (m: Char in mask.toCharArray()) {
                if (m != '#' && str.length > 1) {
                    fieldWithMask += m
                    continue
                }
                try {
                    fieldWithMask += str.get(i)
                } catch (e: Exception) {
                    break
                }
                i++
            }
            return fieldWithMask
        }

    }
}