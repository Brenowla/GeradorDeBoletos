package com.example.geradorboletos

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.geradorboletos.databinding.ActivityMainBinding
import com.example.geradorboletos.retrofit.RetrofitConstants
import com.example.geradorboletos.retrofit.service.AuthorizeService
import com.example.geradorboletos.session.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var authenticationService: AuthorizeService

    var autentication = Autentication()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GeradorBoletosApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.autentication = autentication



        autentication.token = "Token"

    }

    class Autentication(
        var token: String = ""
    )

}