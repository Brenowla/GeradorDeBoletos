package com.example.geradorboletos.ui.failure.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.failure.FailureViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FailureModule {

    @Binds
    @IntoMap
    @ViewModelKey(FailureViewModel::class)
    fun bindFailureViewModel(viewModel: FailureViewModel) : ViewModel

}