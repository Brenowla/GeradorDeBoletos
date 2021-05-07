package com.example.geradorboletos.ui.databinding

import androidx.lifecycle.MutableLiveData
import com.example.geradorboletos.models.Item

class ItemBinding(
    private var item: Item,
    val name: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = item.name
    },
    val value: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = ""
    } ,
    val amount : MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = item.amount.toString()
    }
) {

    fun update(item: Item) {
        this.item = item
        name.value = this.item.name
        value.value = this.item.value.toString()
        amount.value = this.item.amount.toString()
    }

    fun toItem(): Item{
        this.item = Item(
            name = name.value ?: "",
            value = value.value?.toInt() ?: 0,
            amount = amount.value?.toInt() ?: 1
        )
        return this.item
    }

}