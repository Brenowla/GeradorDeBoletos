package com.example.geradorboletos.ui.list_person.di

import com.example.geradorboletos.ui.list_person.ListPersonFragment
import dagger.Subcomponent

@Subcomponent(modules = [ListPersonModule::class])
interface ListPersonComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListPersonComponent
    }

    fun inject(fragment: ListPersonFragment)

}