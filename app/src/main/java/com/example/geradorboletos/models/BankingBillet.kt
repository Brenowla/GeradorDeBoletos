package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName

class BankingBillet(
    val customer: Person,
    @SerializedName("expire_at")
    val expireAt: String

) {
}