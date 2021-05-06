package com.example.geradorboletos.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants

@Entity(tableName = GeradorBoletosDatabaseConstants.Tables.PERSON_TABLE, indices = [Index(value = ["cpf"], unique = true), Index(value = ["cnpj"], unique = true)])
class PersonData(
    @Embedded
    val person: Person,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) {
}