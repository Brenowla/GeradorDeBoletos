package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName

class Payment(
    @SerializedName("banking_billet")
    val bankingBillet: BankingBillet
)

class Charge(
    val items: List<Item>,
    val payment: Payment
) {
}