package com.example.geradorboletos.ui.form_person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.ui.databinding.PersonBinding
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
    }

    fun switchIsJuridical(change: Boolean){
        isJuridical.value = change
        formPersonValidators.updateJuridical(change, personBinding.name.value, personBinding.cpf.value)
    }

    fun getPerson() = personBinding.toPerson(isJuridical = isJuridical.value?:false, hasAdress = hasAdress.value?:false)

    fun updatePerson(person: Person){
        personBinding.update(person)
        setValidFieldsForInputPerson(person)
    }

    private fun setValidFieldsForInputPerson(person: Person) {
        if(person.name != null ) formPersonValidators.name.value = true
        if(person.cpf != null) formPersonValidators.cpf.value = true
        if(person.phoneNumber != null) formPersonValidators.phone.value = true
        if(person.juridicalPerson != null){
            formPersonValidators.cnpj.value = true
            formPersonValidators.corporateName.value = true
        }
        //Fazer para o address
        formPersonValidators.verifyRight()
    }

    fun verifyName(): Boolean {
        if(empty(personBinding.name.value)) return false
        return true
    }

    fun empty(text: String?): Boolean {
        if (text == null) return false
        return text.isBlank()
    }

}