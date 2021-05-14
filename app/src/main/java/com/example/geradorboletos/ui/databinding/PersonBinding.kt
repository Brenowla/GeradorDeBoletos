package com.example.geradorboletos.ui.databinding

import androidx.lifecycle.MutableLiveData
import com.example.geradorboletos.models.Adress
import com.example.geradorboletos.models.JuridicalPerson
import com.example.geradorboletos.models.Person
import com.example.geradorboletos.ui.utils.Mask

class PersonBinding(
    private var person: Person,
    val name: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.name ?: ""
    },
    val cpf: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.cpf ?: ""
    },
    val phoneNumber: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.phoneNumber
    },
    val email: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.email ?: ""
    },
    val cnpj: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.juridicalPerson?.cnpj ?: ""
    },
    val corporateName: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.juridicalPerson?.corporateName ?: ""
    },
    val street: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.street ?: ""
    },
    val number: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.number ?: ""
    },
    val neighborhood: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.number ?: ""
    },
    val zipcode: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.zipcode ?: ""
    },
    val city: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.city ?: ""
    },
    val complement: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.complement ?: ""
    },
    val state: MutableLiveData<String> = MutableLiveData<String>().also {
        it.value = person.address?.complement ?: ""
    }
) {

    fun update(person: Person) {
        this.person = person
        name.value = this.person.name
        cpf.value = this.person.cpf
        phoneNumber.value = this.person.phoneNumber
        email.value = this.person.email
        cnpj.value = this.person.juridicalPerson?.cnpj ?: ""
        corporateName.value = this.person.juridicalPerson?.corporateName ?: ""
        street.value = this.person.address?.street ?: ""
        number.value = this.person.address?.number ?: ""
        neighborhood.value = this.person.address?.neighborhood ?: ""
        zipcode.value = this.person.address?.zipcode ?: ""
        city.value = this.person.address?.city ?: ""
        complement.value = this.person.address?.complement ?: ""
        state.value = this.person.address?.state ?: ""
    }

    fun toPerson(isJuridical: Boolean, hasAdress: Boolean): Person {
        val juridicalPerson = if (isJuridical) {
            JuridicalPerson(cnpj = cnpj.value?.let { Mask.replaceChars(it) } ?: "", corporateName = corporateName.value ?: "")
        } else {
            null
        }
        val address = if (hasAdress) {
            Adress(
                street = street.value ?: "",
                number = number.value ?: "",
                neighborhood = neighborhood.value ?: "",
                zipcode = zipcode.value?.let { Mask.replaceChars(it) } ?: "",
                city = city.value ?: "",
                complement = if (complement.value == "") null else complement.value,
                state = state.value ?: ""
            )
        } else {
            null
        }
        if(name.value == ""){
            name.value = null
        }
        if(cpf.value == ""){
            cpf.value = null
        }
        this.person = Person(
            name = name.value,
            cpf = cpf.value?.let { Mask.replaceChars(it) },
            phoneNumber = phoneNumber.value?.let { Mask.replaceChars(it) } ?: "",
            email = email.value ?: "",
            juridicalPerson = juridicalPerson,
            address = address
        )
        return this.person
    }

}