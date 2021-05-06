package com.example.geradorboletos.retrofit

class RetrofitConstants private constructor(){

    //URLs
    object URL {
        const val BASE = "https://sandbox.gerencianet.com.br/v1/"
        const val AUTHORIZE = "authorize"
        const val CHARGE_BILLET = "charge/one-step"
    }

    object Credentials {
        const val CLIENT_ID = "Client_Id_4157a108ed28612a9b60bcea6ccb8715a032757a"
        const val CLIENT_SECRET = "Client_Secret_b32eb85955b615420be29a2ae334189135aa7541"
        const val GRANT_TYPE = "client_credentials"
    }

    object ErrorMessages {
        const val FAILURE_API = "Sem resposta do servidor"
    }


}