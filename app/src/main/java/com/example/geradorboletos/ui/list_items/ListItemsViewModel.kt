package com.example.geradorboletos.ui.list_items

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.repositories.database.ItemRepository
import javax.inject.Inject

class ListItemsViewModel @Inject constructor(private val itemRepository: ItemRepository): ViewModel() {

    val allItems: LiveData<List<Item>> = itemRepository.getAllItems()
    val allItensUpdated: MutableLiveData<List<Item>> = MutableLiveData()

    fun filterNames(search: Editable) {
        val search = search.toString()
        allItensUpdated.value = allItems.value?.filter { item ->
            item.name.toLowerCase().startsWith(search.toLowerCase())
        }
    }
}