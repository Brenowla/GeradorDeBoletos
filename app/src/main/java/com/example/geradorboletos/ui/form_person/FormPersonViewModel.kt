package com.example.geradorboletos.ui.form_person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.ui.databinding.PersonBinding
import com.example.geradorboletos.ui.utils.ListStates
import javax.inject.Inject

class FormPersonViewModel @Inject constructor() : ViewModel() {

    val personBinding: PersonBinding by lazy {
        PersonBinding(Person())
    }
    val isJuridical: MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = false
    }
    val hasAdress: MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = false
    }
    val formPersonValidators = FormPersonValidators()

    fun onSwitchAdressClick(checked: Boolean){
        hasAdress.value = checked
        formPersonValidators.updateAdress(checked)
    }

    fun switchIsJuridical(change: Boolean){
        isJuridical.value = change
        formPersonValidators.updateJuridical(change, personBinding.name.value, personBinding.cpf.value)
    }

    fun getPerson() = personBinding.toPerson(isJuridical = isJuridical.value?:false, hasAdress = hasAdress.value?:false)

    fun updatePerson(person: Person){
        personBinding.update(person)
        if(person.juridicalPerson!= null){
            switchIsJuridical(true)
        }
        if (person.address != null){
            onSwitchAdressClick(true)
        }
        formPersonValidators.setValidFieldsForInputPerson(person)
    }

    fun invalidState() {
        personBinding.state.value = ""
        formPersonValidators.stateValidator(false)
    }

    fun validState(position: Int) {
        personBinding.state.value = ListStates.States[position].substring(1,3)
        formPersonValidators.stateValidator(true)
    }

}