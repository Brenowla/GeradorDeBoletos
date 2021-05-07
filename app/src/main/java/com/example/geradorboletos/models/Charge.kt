package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Payment(
    @SerializedName("banking_billet")
    val bankingBillet: BankingBillet
) : Serializable

class Charge(
    val items: List<Item>,
    val payment: Payment
): Serializable {
}