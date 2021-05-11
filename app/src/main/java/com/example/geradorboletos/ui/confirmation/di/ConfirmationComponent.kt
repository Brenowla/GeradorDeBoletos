package com.example.geradorboletos.ui.confirmation.di

import com.example.geradorboletos.ui.confirmation.ConfirmationFragment
import dagger.Subcomponent

@Subcomponent(modules = [ConfirmationModule::class])
interface ConfirmationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConfirmationComponent
    }

    fun inject(fragment: ConfirmationFragment)

}