package com.example.geradorboletos.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.geradorboletos.database.GeradorBoletosDatabaseConstants
import com.google.gson.annotations.SerializedName

@Entity(tableName = GeradorBoletosDatabaseConstants.Tables.PERSON_TABLE, indices = [Index(value = ["cpf"], unique = true)])
class PersonModel(
    @PrimaryKey(autoGenerate = true)
    private val id: Long,
    private val name: String,
    private val cpf: String,
    @SerializedName("phone_number")
    private val phoneNumber: String,
    private val email: String,
    @Embedded
    @SerializedName("juridical_person")
    private val juridicalPerson: JuridicalPerson?,
    @Embedded
    private val address: AdressModel?
) {

}