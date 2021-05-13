package com.example.geradorboletos.ui.list_items.di

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.di.ViewModelKey
import com.example.geradorboletos.ui.list_items.ListItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ListItemsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListItemsViewModel::class)
    fun bindsListItemsModel(viewModel: ListItemsViewModel) : ViewModel

}