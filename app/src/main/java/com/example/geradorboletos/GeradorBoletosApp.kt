package com.example.geradorboletos

import android.app.Application
import com.example.geradorboletos.di.ApplicationComponent
import com.example.geradorboletos.di.DaggerApplicationComponent

class GeradorBoletosApp : Application() {

    companion object{
        lateinit var appComponent: ApplicationComponent
    }


    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)
    }



}