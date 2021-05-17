package com.example.geradorboletos.ui.add_items

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.ui.databinding.ItemBinding
import javax.inject.Inject

class AddItemsViewModel @Inject constructor(context: Context) : ViewModel() {

    val itemBinding = ItemBinding(Item())
    private var listItems = arrayListOf<Item>()
    val listItemsData: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>().also {
        it.value = listItems
    }
    var listSize = 0

    fun updateList(items: ArrayList<Item>){
        listItems = items
        listItemsData.value = listItems
        itemBinding.update(Item())
        listSize = listItems.size
    }

    fun addItem() {
        listItems.add(itemBinding.toItem())
        listItemsData.value = listItems
        itemBinding.update(Item())
        listSize = listItems.size
    }

    fun getLista() : ArrayList<Item> {
        return listItems
    }

    fun setEditableItem(position: Int){
        itemBinding.update(listItems[position])
    }

    fun getItemName(position: Int) : String {
        return listItems[position].name
    }

    fun edit(position: Int): Boolean {
        listItems[position] = itemBinding.toItem()
        listItemsData.value = listItems
        itemBinding.update(Item())
        listSize = listItems.size
        return true
    }

    fun delete(position: Int): Boolean {
        listItems.removeAt(position)
        listItemsData.value = listItems
        listSize = listItems.size
        return true
    }

    fun addQtd() {
        itemBinding.amount.value =  (itemBinding.amount.value?.toInt()?.plus(1)).toString()
    }

    fun removeQtd() {
        val newValue = (itemBinding.amount.value?.toInt()?.minus(1))
        if(newValue != null && newValue >= 1){
            itemBinding.amount.value = newValue.toString()
        }
    }
}