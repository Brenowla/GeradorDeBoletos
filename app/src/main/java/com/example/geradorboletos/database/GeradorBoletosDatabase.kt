package com.example.geradorboletos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geradorboletos.database.dao.ItemDAO
import com.example.geradorboletos.database.dao.PersonDAO
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.models.PersonData

@Database(entities = [PersonData::class, Item::class], version = 1, exportSchema = false)
abstract class GeradorBoletosDatabase : RoomDatabase() {

    abstract val personDAO: PersonDAO

    abstract val itemDAO: ItemDAO

}