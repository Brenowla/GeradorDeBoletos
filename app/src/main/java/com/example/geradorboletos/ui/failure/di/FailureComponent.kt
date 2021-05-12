package com.example.geradorboletos.ui.failure.di

import com.example.geradorboletos.ui.failure.FailureFragment
import dagger.Subcomponent

@Subcomponent(modules = [FailureModule::class])
interface FailureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : FailureComponent
    }

    fun inject(fragment: FailureFragment)

}