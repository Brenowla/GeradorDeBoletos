package com.example.geradorboletos.models

import com.google.gson.annotations.SerializedName

class Token (
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("expire_at")
    val expireAt: String,
    @SerializedName("token_type")
    val tokenType: String
)
