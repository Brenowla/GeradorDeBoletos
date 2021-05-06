package com.example.geradorboletos.ui.form_person.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.form_person.FormPersonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FormPersonModule {

    @Binds
    @IntoMap
    @ViewModelKey(FormPersonViewModel::class)
    fun bindFormPersonViewModel(viewModel: FormPersonViewModel) : ViewModel

}