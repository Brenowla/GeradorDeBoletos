package com.example.geradorboletos.ui.confirmation

import androidx.lifecycle.ViewModel
import com.example.geradorboletos.models.ChargeResponse
import com.example.geradorboletos.ui.utils.MoneyMask
import javax.inject.Inject

class ConfirmationViewModel @Inject constructor() : ViewModel() {

    fun makeSharedText(chargeResponse: ChargeResponse?): String {
        var text = ""
        chargeResponse?.let {  charge ->
            text = "Cobrança no valor de ${MoneyMask.imediateMask("R$",charge.data.total.toString())}.\nCódigo de barras: ${charge.data.barcode}.\nDisponível em: ${charge.data.pdf.charge}"
        }
        return text
    }

}