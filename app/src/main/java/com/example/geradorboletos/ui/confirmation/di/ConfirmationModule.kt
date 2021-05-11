package com.example.geradorboletos.ui.confirmation.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.confirmation.ConfirmationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ConfirmationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmationViewModel::class)
    fun bindConfirmationViewModel(viewModel: ConfirmationViewModel) : ViewModel

}