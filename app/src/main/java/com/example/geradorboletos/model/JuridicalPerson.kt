package com.example.geradorboletos.model

import com.google.gson.annotations.SerializedName

class JuridicalPerson(
    private val cnpj: String,
    @SerializedName("corporate_name")
    private val corporateName: String
) {
}