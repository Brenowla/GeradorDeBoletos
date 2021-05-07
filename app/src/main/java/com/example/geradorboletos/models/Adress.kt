package com.example.geradorboletos.models

import java.io.Serializable

class Adress(
    val street: String,
    val number: String,
    val neighborhood: String,
    val zipcode: String,
    val city: String,
    val complement: String?,
    val state: String
) : Serializable {

}