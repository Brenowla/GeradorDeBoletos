package com.example.geradorboletos.ui.add_items.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.add_items.AddItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddItemsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddItemsViewModel::class)
    fun bindFormPersonViewModel(viewModel: AddItemsViewModel) : ViewModel

}