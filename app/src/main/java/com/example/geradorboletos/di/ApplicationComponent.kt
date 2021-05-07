package com.example.geradorboletos.di

import android.content.Context
import com.example.geradorboletos.MainActivity
import com.example.geradorboletos.database.di.DatabaseModule
import com.example.geradorboletos.retrofit.di.RetrofitModule
import com.example.geradorboletos.ui.add_items.di.AddItemsComponent
import com.example.geradorboletos.ui.aditional_information.di.AditionalItensComponent
import com.example.geradorboletos.ui.form_person.di.FormPersonComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class, DatabaseModule::class, SubComponentsModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    fun formPersonComponent(): FormPersonComponent.Factory

    fun addItemsComponent(): AddItemsComponent.Factory

    fun aditionalItensComponent() : AditionalItensComponent.Factory

    fun inject(mainActivity: MainActivity)

}

@Module(subcomponents = [FormPersonComponent::class, AddItemsComponent::class, AditionalItensComponent::class])
object SubComponentsModule