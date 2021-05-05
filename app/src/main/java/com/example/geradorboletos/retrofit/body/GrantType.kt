package com.example.geradorboletos.retrofit.body

import com.example.geradorboletos.retrofit.RetrofitConstants
import com.google.gson.annotations.SerializedName

class GrantType {
    @SerializedName("grant_type")
    val grantType = RetrofitConstants.Credentials.GRANT_TYPE
}