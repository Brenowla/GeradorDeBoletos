package com.example.geradorboletos.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Person(
    val name: String? = null,
    val cpf: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String = "",
    val email: String? = null,
    @SerializedName("juridical_person")
    @Embedded
    val juridicalPerson: JuridicalPerson? = null,
    @Embedded
    val address: Adress? = null
) : Serializable {

}