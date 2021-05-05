package com.example.geradorboletos.retrofit.callback

import com.example.geradorboletos.retrofit.RetrofitConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaseCallback<T>(private val responseCallback: ResponseCallback<T>) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful){
            val result = response.body()
            result?.let { responseCallback.inSucess(it) }
        }else {
            responseCallback.inFailure(response.code().toString())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        responseCallback.inFailure("${RetrofitConstants.ErrorMessages.FAILURE_API} ${t.message}" )
    }

    interface ResponseCallback<T> {
        fun inSucess(result: T)
        fun inFailure(error: String)
    }
}