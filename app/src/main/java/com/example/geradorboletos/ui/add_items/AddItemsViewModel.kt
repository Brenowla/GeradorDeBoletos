package com.example.geradorboletos.ui.add_items

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.ui.databinding.ItemBinding
import javax.inject.Inject

class AddItemsViewModel @Inject constructor(context: Context) : ViewModel() {

    val itemBinding = ItemBinding(Item())
    private val listItems = arrayListOf<Item>()
    val listItemsData: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>().also {
        it.value = listItems
    }
    var listSize = 0

    fun additem() {
        listItems.add(itemBinding.toItem())
        listItemsData.value = listItems
        itemBinding.update(Item())
        listSize = listItems.size
    }

}