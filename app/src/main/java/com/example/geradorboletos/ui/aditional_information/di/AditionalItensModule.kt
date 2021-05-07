package com.example.geradorboletos.ui.aditional_information.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.aditional_information.AditionalInformationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AditionalItensModule {

    @Binds
    @IntoMap
    @ViewModelKey(AditionalInformationViewModel::class)
    fun bindAditionalInformationViewModel(viewModel: AditionalInformationViewModel) : ViewModel

}