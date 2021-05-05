package com.example.geradorboletos.retrofit.interceptor

import android.content.Context
import com.example.geradorboletos.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            // Se o token ja foi salvo, adiciona ele na chamada da requisição
            sessionManager.fetchAuthToken()?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
            return chain.proceed(requestBuilder.build())
    }

}