package com.example.geradorboletos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geradorboletos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val toolbar by lazy {
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GeradorBoletosApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this

    }



}