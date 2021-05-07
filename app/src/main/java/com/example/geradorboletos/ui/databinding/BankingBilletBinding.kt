package com.example.geradorboletos.ui.databinding

import androidx.lifecycle.MutableLiveData
import com.example.geradorboletos.models.BankingBillet
import com.example.geradorboletos.models.Person

class BankingBilletBinding(
    private var bankingBillet: BankingBillet? = null,
    val expireAt: MutableLiveData<String> =  MutableLiveData<String>().also {
        it.value = bankingBillet?.expireAt ?: ""
    }
) {

    fun update(bankingBillet: BankingBillet) {
        this.bankingBillet = bankingBillet
        expireAt.value = this.bankingBillet?.expireAt
    }

    fun toBankingBillet() : BankingBillet{
        bankingBillet = BankingBillet(bankingBillet?.customer ?: Person(), expireAt.value?: "")
        return bankingBillet?: BankingBillet(Person())
    }

}