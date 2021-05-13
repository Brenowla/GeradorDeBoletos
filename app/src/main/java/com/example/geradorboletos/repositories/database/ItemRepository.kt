package com.example.geradorboletos.repositories.database

import com.example.geradorboletos.database.dao.ItemDAO
import com.example.geradorboletos.models.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDAO: ItemDAO) {

    fun saveItem(items : List<Item>) {
        itemDAO.save(items)
    }

    fun getAllItems() = itemDAO.searchAll()

}