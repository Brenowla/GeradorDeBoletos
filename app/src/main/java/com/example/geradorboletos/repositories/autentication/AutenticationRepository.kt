package com.example.geradorboletos.repositories.autentication

import android.util.Base64
import com.example.geradorboletos.models.Token
import com.example.geradorboletos.retrofit.RetrofitConstants
import com.example.geradorboletos.retrofit.body.GrantType
import com.example.geradorboletos.retrofit.callback.BaseCallback
import com.example.geradorboletos.retrofit.service.AutenticateService
import javax.inject.Inject

class AutenticationRepository @Inject constructor(private val autenticateService: AutenticateService) {

    fun autenticate(responseCallback: BaseCallback.ResponseCallback<Token>){
        val credentials = "${RetrofitConstants.Credentials.CLIENT_ID}:${RetrofitConstants.Credentials.CLIENT_SECRET}".encodeToByteArray()
        val credentialsInBase64 = "Basic ${Base64.encodeToString(credentials, Base64.NO_WRAP)}"
        val call = autenticateService.autenticate(credentialsInBase64, GrantType())
        call.enqueue(BaseCallback<Token>(responseCallback))
    }



}