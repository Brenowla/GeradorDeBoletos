package com.example.geradorboletos.retrofit.service

import com.example.geradorboletos.models.Charge
import com.example.geradorboletos.models.ChargeResponse
import com.example.geradorboletos.retrofit.RetrofitConstants
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.POST

interface ChargeService {

    @POST(RetrofitConstants.URL.CHARGE_BILLET)
    fun makeCharge(@Body charge: Charge): Call<ChargeResponse>

}