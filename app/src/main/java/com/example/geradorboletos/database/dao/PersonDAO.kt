package com.example.geradorboletos.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants
import com.example.geradorboletos.models.PersonData

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(person: PersonData)

    @Query("SELECT * FROM ${GeradorBoletosDatabaseConstants.Tables.PERSON_TABLE}")
    fun searchAll(): LiveData<List<PersonData>>

}