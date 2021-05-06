package com.example.geradorboletos.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants
import com.example.geradorboletos.models.Item

@Dao
interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(items: List<Item>)

    @Query("SELECT * FROM ${GeradorBoletosDatabaseConstants.Tables.ITEM_TABLE}")
    fun searchAll(): LiveData<List<Item>>

}