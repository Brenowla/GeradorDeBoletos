package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class JuridicalPerson(
    val cnpj: String,
    @SerializedName("corporate_name")
    val corporateName: String
) : Serializable {
}