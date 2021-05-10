package com.example.geradorboletos.ui.send_charge.di

import com.example.geradorboletos.ui.send_charge.SendChargeFragment
import dagger.Subcomponent

@Subcomponent(modules = [SendChargeModule::class])
interface SendChargeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SendChargeComponent
    }

    fun inject(fragment: SendChargeFragment)

}