package com.example.geradorboletos.ui.aditional_information

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.ui.utils.validators.DateValidator
import javax.inject.Inject

class AditionalInformationViewModel @Inject constructor() : ViewModel() {

    val expireAt = MutableLiveData<String>().also {
        it.value = ""
    }
    val validExpireAt = MutableLiveData<Boolean>()
//    val isRight : MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
//        it.value = false
//    }
    var totalValue = 0

    fun updateExpireAt(expireAt: String){
        this.expireAt.value = expireAt
    }

    fun updateTotal(items: List<Item>){
        totalValue = 0
        for (i in items) {
            totalValue += i.value*i.amount
        }
    }

    fun validateExpireAt(expireAt: Editable){
        val str = expireAt.toString()
        this.validExpireAt.value = DateValidator.isValid(str)
    }
}