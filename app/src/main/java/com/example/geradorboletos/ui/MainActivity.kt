package com.example.geradorboletos.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.ActivityMainBinding
import com.example.geradorboletos.ui.di.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var mainComponent: MainComponent
    }

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelProvider }

    private val toolbar by lazy {
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = GeradorBoletosApp.appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }



}
