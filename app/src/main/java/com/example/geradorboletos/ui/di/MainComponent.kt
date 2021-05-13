package com.example.geradorboletos.ui.di

import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.add_items.di.AddItemsComponent
import com.example.geradorboletos.ui.aditional_information.di.AditionalItensComponent
import com.example.geradorboletos.ui.confirmation.di.ConfirmationComponent
import com.example.geradorboletos.ui.failure.di.FailureComponent
import com.example.geradorboletos.ui.form_person.di.FormPersonComponent
import com.example.geradorboletos.ui.list_items.di.ListItemComponent
import com.example.geradorboletos.ui.list_person.di.ListPersonComponent
import com.example.geradorboletos.ui.send_charge.di.SendChargeComponent
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [SubComponentsModuleMain::class, MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : MainComponent
    }

    fun formPersonComponent(): FormPersonComponent.Factory

    fun addItemsComponent(): AddItemsComponent.Factory

    fun aditionalItensComponent() : AditionalItensComponent.Factory

    fun sendChargeComponent() : SendChargeComponent.Factory

    fun confirmationComponent() : ConfirmationComponent.Factory

    fun failureComponent() : FailureComponent.Factory

    fun listPersonComponent() : ListPersonComponent.Factory

    fun listItemComponent() : ListItemComponent.Factory

    fun inject(mainActivity: MainActivity)
}

@Module(subcomponents = [
    FormPersonComponent::class,
    AddItemsComponent::class,
    AditionalItensComponent::class,
    SendChargeComponent::class,
    ConfirmationComponent::class,
    FailureComponent::class,
    ListPersonComponent::class,
    ListItemComponent::class
])
object SubComponentsModuleMain