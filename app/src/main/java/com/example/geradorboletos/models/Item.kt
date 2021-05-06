package com.example.geradorboletos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item(
    @PrimaryKey
    val name: String,
    val value: Int,
    val amount: Int
) {
}