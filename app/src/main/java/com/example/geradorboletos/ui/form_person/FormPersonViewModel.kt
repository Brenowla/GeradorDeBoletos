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

    fun onSwitchAdressClick(checked: Boolean){
        hasAdress.value = checked
    }

    fun switchIsJuridical(change: Boolean){
        isJuridical.value = change
    }

    fun getPerson() = personBinding.toPerson(isJuridical = isJuridical.value?:false, hasAdress = hasAdress.value?:false)

    fun updatePerson(person: Person){
        personBinding.update(person)
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