package com.example.geradorboletos.ui.form_person

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.ui.utils.validators.*

class FormPersonValidators {

    val name : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val cpf : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val phone : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val email : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val cnpj : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val corporateName: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val street: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val number: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val neighborhood: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val zipcode: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val city: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val complement: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val state: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val isRight : MutableLiveData<Boolean> = MutableLiveData<Boolean>().also {
        it.value = false
    }

    private var isJuridical = false
    private var hasAdress = false

    fun nameValidator(name: Editable) {
        val str = name.toString()
        val oldValue = this.name.value
        val newValue = (isJuridical && empty(str)) || !empty(str)
        this.name.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun cpfValidator(cpf: Editable) {
        val str = cpf.toString()
        val oldValue = this.cpf.value
        val newValue = (isJuridical && empty(str)) || CPFValidator.isValid(str)
        this.cpf.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun phoneValidator(phone: Editable) {
        val str = phone.toString()
        Log.i("Phone", str)
        val oldValue = this.phone.value
        val newValue = PhoneValidator.isValid(str)
        this.phone.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun emailValidator(email: Editable) {
        val str = email.toString()
        val oldValue = this.email.value
        val newValue = EmailValidator.isValid(str) || empty(str)
        this.email.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun cnpjValidator(cnpj: Editable){
        val str = cnpj.toString()
        val oldValue = this.cnpj.value
        val newValue = CNPJValidator.isValid(str)
        this.cnpj.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun corporateNameValidator(corporateName : Editable) {
        val str = corporateName.toString()
        val oldValue = this.corporateName.value
        val newValue = !empty(str)
        this.corporateName.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun streetValidator(street: Editable) {
        val str = street.toString()
        val oldValue = this.street.value
        val newValue = (isJuridical && empty(str)) || !empty(str)
        this.street.value = newValue
        if (oldValue != newValue) verifyRight()
    }


    fun numberValidator(number: Editable) {
        val str = number.toString()
        val oldValue = this.number.value
        val newValue = !empty(str)
        this.number.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun neighborhoodValidator(neighborhood: Editable) {
        val str = neighborhood.toString()
        val oldValue = this.neighborhood.value
        val newValue = !empty(str)
        this.neighborhood.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun zipcodeValidator(zipcode: Editable) {
        val str = zipcode.toString()
        val oldValue = this.zipcode.value
        val newValue = CEPValidator.isValid(str)
        this.zipcode.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun cityValidator(city: Editable) {
        val str = city.toString()
        val oldValue = this.city.value
        val newValue = !empty(str)
        this.city.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun complementValidator(complement: Editable) {
        val str = complement.toString()
        val oldValue = this.complement.value
        val newValue = !empty(str)
        this.complement.value = newValue
        if (oldValue != newValue) verifyRight()
    }

    fun stateValidator(b: Boolean) {
        val oldValue = this.state.value
        this.state.value = b
        if(oldValue != b) verifyRight()
    }

    fun verifyRight() {
        isRight.value = personValues() && juridicalValues() && adressValues()
    }

    private fun adressValues() =
        (!hasAdress || (street.value ?: false && number.value ?: false && neighborhood.value ?: false && zipcode.value ?: false && city.value ?: false && state.value ?: false))

    private fun juridicalValues() = (!isJuridical || (cnpj.value ?: false && corporateName.value ?: false))

    private fun personValues() = (name.value ?: false
            && cpf.value ?: false
            && phone.value ?: false)

    private fun empty(text: String?): Boolean {
        if (text == null) return true
        return text.isBlank()
    }

    fun updateJuridical(change: Boolean, name: String?, cpf: String?) {
        isJuridical = change
        if(isJuridical){
            if(empty(name)){
                this.name.value = true
            }
            if(empty(cpf)){
                this.cpf.value = true
            }
        }else {
            if(empty(name)){
                this.name.value = false
            }
            if(empty(cpf)){
                this.cpf.value = false
            }
        }
        verifyRight()
    }

    fun updateAdress(change: Boolean){
        hasAdress = change
        verifyRight()
    }

    fun setValidFieldsForInputPerson(person: Person) {
        if(person.name != null ) name.value = true
        if(person.cpf != null) cpf.value = true
        if(person.phoneNumber != null) phone.value = true
        if(person.email != null) email.value = true
        if(person.juridicalPerson != null){
            cnpj.value = true
            corporateName.value = true
        }
        //Fazer para o address
        verifyRight()
    }


}