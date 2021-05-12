package com.example.geradorboletos.di

import android.content.Context
import com.example.geradorboletos.database.di.DatabaseModule
import com.example.geradorboletos.retrofit.di.RetrofitModule
import com.example.geradorboletos.ui.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class, DatabaseModule::class, SubComponentsModule::class, ViewModelBuilderModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    fun mainComponent() : MainComponent.Factory

}

@Module(subcomponents = [
    MainComponent::class
])
object SubComponentsModule