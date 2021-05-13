package com.example.geradorboletos.ui.list_items.di

import com.example.geradorboletos.ui.list_items.ListItemsFragment
import dagger.Subcomponent

@Subcomponent (modules = [ListItemsModule::class])
interface ListItemComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListItemComponent
    }

    fun inject(fragment: ListItemsFragment)

}