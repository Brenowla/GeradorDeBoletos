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
        it.value = true
    }
    val hasAdress: MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = false
    }


    fun onSwitchAdressClick(checked: Boolean){
        hasAdress.value = checked
    }

    fun onClickFisical(){

    }

    fun onClickJuridical(){

    }

}