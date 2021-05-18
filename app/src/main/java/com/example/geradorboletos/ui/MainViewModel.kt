package com.example.geradorboletos.ui

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.ChargeResponse
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.models.Person
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel(){

    var costumer: Person? = null
    var items: ArrayList<Item> = arrayListOf()
    var expireAt: String? = null
    var chargeResponse: ChargeResponse? = null

    fun resetData(){
        costumer = null
        items = arrayListOf()
        expireAt = null
        chargeResponse = null
    }

}
