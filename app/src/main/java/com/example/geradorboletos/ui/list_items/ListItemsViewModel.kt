package com.example.geradorboletos.ui.list_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.repositories.database.ItemRepository
import javax.inject.Inject

class ListItemsViewModel @Inject constructor(private val itemRepository: ItemRepository): ViewModel() {

    val allItems: LiveData<List<Item>> = itemRepository.getAllItems()

}