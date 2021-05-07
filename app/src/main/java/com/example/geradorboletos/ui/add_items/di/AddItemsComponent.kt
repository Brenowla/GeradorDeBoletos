package com.example.geradorboletos.ui.add_items.di

import com.example.geradorboletos.ui.add_items.AddItemsFragment
import dagger.Subcomponent

@Subcomponent(modules = [AddItemsModule::class])
interface AddItemsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddItemsComponent
    }

    fun inject(fragment: AddItemsFragment)

}