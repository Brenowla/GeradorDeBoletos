package com.example.geradorboletos.ui.list_person.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.list_person.ListPersonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListPersonModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListPersonViewModel::class)
    fun bindListPersonViewModel(view: ListPersonViewModel): ViewModel

}