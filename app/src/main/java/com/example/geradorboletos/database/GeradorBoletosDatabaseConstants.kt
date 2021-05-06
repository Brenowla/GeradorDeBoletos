package com.example.geradorboletos.database

class GeradorBoletosDatabaseConstants private constructor(){

    // Nome DB
    companion object{
        const val DB_NAME = "gerador_boletos.db"
    }

    object Tables {
        const val PERSON_TABLE = "person"
        const val ITEM_TABLE = "item"
    }

}