package com.example.geradorboletos.retrofit.service

import com.example.geradorboletos.model.TokenModel
import com.example.geradorboletos.retrofit.RetrofitConstants
import com.example.geradorboletos.retrofit.body.GrantType
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AutenticateService {

    @POST(RetrofitConstants.URL.AUTHORIZE)
    fun autenticate(@Header("Authorization") credentials: String, @Body grant_type: GrantType): Call<TokenModel>

}