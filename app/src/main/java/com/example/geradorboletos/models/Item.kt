package com.example.geradorboletos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Item(
    @PrimaryKey
    val name: String = "",
    val value: Int = 0,
    val amount: Int = 1
) : Serializable{
}