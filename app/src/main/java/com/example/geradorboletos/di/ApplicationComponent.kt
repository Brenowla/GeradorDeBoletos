package com.example.geradorboletos.di

import android.content.Context
import com.example.geradorboletos.MainActivity
import com.example.geradorboletos.database.di.DatabaseModule
import com.example.geradorboletos.retrofit.di.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, DatabaseModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    fun inject(mainActivity: MainActivity)

}