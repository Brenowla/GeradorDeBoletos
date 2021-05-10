package com.example.geradorboletos.ui.send_charge.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.send_charge.SendChargeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SendChargeModule {

    @Binds
    @IntoMap
    @ViewModelKey(SendChargeViewModel::class)
    fun bindSendCharViewModel(viewModel: SendChargeViewModel) : ViewModel


}