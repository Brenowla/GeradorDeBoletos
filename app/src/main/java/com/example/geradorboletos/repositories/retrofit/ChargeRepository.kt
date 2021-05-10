package com.example.geradorboletos.repositories.retrofit

import com.example.geradorboletos.models.Charge
import com.example.geradorboletos.models.ChargeResponse
import com.example.geradorboletos.retrofit.callback.BaseCallback
import com.example.geradorboletos.retrofit.service.ChargeService
import javax.inject.Inject

class ChargeRepository @Inject constructor(private val chargeService: ChargeService) {

    fun makeCharge(charge: Charge, token: String,responseCallback: BaseCallback.ResponseCallback<ChargeResponse>){
        val call = chargeService.makeCharge(token,charge)
        call.enqueue(BaseCallback(responseCallback))
    }

}