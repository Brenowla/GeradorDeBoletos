package com.example.geradorboletos.ui.aditional_information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import javax.inject.Inject

class AditionalInformationViewModel @Inject constructor() : ViewModel() {

    val expireAt = MutableLiveData<String>().also {
        it.value = ""
    }
    var totalValue = 0

    fun updateExpireAt(expireAt: String){
        this.expireAt.value = expireAt
    }

    fun updateTotal(items: List<Item>){
        totalValue = 0
        for (i in items) {
            totalValue += i.value
        }
    }

}