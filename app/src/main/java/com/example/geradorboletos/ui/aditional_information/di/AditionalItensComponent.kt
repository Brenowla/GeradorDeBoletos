package com.example.geradorboletos.ui.aditional_information.di

import com.example.geradorboletos.ui.aditional_information.AditionalInformationFragment
import dagger.Subcomponent

@Subcomponent(modules = [AditionalItensModule::class])
interface AditionalItensComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AditionalItensComponent
    }

    fun inject(fragment: AditionalInformationFragment)

}