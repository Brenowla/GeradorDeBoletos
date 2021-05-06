package com.example.geradorboletos.ui.form_person.di

import com.example.geradorboletos.ui.form_person.FormPersonFragment
import dagger.Subcomponent

@Subcomponent(modules = [FormPersonModule::class])
interface FormPersonComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FormPersonComponent
    }

    fun inject(fragment: FormPersonFragment)
}