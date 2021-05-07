package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChargeResponse(
    val code: Int,
    val data: Data,
    @SerializedName("expire_at")
    val expireAt: String,
    @SerializedName("charge_id")
    val chargeId: Int,
    val status: String,
    val total: Int,
    val payment: String
) : Serializable {
}

class Data(
    val barcode: String,
    val link: String,
    val pdf: PDF
)

class PDF(val charge: String)