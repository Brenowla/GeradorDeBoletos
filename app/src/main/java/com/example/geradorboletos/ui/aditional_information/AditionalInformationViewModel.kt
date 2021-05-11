package com.example.geradorboletos.ui.aditional_information

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.Item
import com.example.geradorboletos.ui.databinding.BankingBilletBinding
import javax.inject.Inject

class AditionalInformationViewModel @Inject constructor() : ViewModel() {

    var bankingBilletData = BankingBilletBinding()
    var totalValue = 0

    fun updateTotal(items: List<Item>){
        totalValue = 0
        for (i in items) {
            totalValue += i.value
        }
    }

}