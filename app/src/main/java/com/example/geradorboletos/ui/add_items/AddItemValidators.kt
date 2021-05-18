package com.example.geradorboletos.ui.add_items

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import com.example.geradorboletos.ui.utils.validators.ValueValidator

class AddItemValidators {

    val name: MutableLiveData<Boolean> = MutableLiveData()
    val value: MutableLiveData<Boolean> = MutableLiveData()

    val isRight : MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun nameValidator(name: Editable) {
        val str = name.toString()
        val oldValue = this.name.value
        val newValue = !empty(str)
        this.name.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun valueValidator(value: Editable) {
        val str = value.toString()
        val oldValue = this.value.value
        val newValue = ValueValidator.isValid(str)
        this.value.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    private fun empty(text: String?): Boolean {
        if (text == null) return true
        return text.isBlank()
    }

    private fun verifyRight() {
        isRight.value = name.value?: false && value.value?: false
    }
}
