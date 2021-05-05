package com.example.geradorboletos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geradorboletos.databinding.ActivityMainBinding
import com.example.geradorboletos.model.TokenModel
import com.example.geradorboletos.repositories.autentication.AutenticationRepository
import com.example.geradorboletos.retrofit.callback.BaseCallback
import com.example.geradorboletos.retrofit.service.AutenticateService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authenticationService: AutenticateService

    @Inject
    lateinit var autenticationRepository: AutenticationRepository


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GeradorBoletosApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        autenticationRepository.autenticate(object : BaseCallback.ResponseCallback<TokenModel> {
            override fun inSucess(result: TokenModel) {
                Toast.makeText(this@MainActivity, result.accessToken, Toast.LENGTH_LONG).show()
            }

            override fun inFailure(error: String) {
                Log.i("Erro", error)
            }

        })


    }



}