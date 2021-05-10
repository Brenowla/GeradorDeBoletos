package com.example.geradorboletos.ui.send_charge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geradorboletos.models.*
import com.example.geradorboletos.repositories.autentication.AutenticationRepository
import com.example.geradorboletos.repositories.database.ItemRepository
import com.example.geradorboletos.repositories.database.PersonRepository
import com.example.geradorboletos.repositories.retrofit.ChargeRepository
import com.example.geradorboletos.resource.Failure
import com.example.geradorboletos.resource.Resource
import com.example.geradorboletos.resource.Sucess
import com.example.geradorboletos.retrofit.callback.BaseCallback
import com.example.geradorboletos.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SendChargeViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val itemRepository: ItemRepository,
    private val autenticationRepository: AutenticationRepository,
    private val chargeRepository: ChargeRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    val callResult: MutableLiveData<Resource<ChargeResponse>> = MutableLiveData()

    fun sendCharge(bankingBillet: BankingBillet, items : List<Item>) {
        savePersonAndItems(bankingBillet, items)

        val charge = Charge(items, Payment(bankingBillet))

        var token = sessionManager.fetchAuthToken()
        if(token != null){
            token = "Bearer $token"
            trySendCharge(charge,token)
        }else{
            autenticate(charge)
        }
        //autenticate(charge)
        //trySendCharge(charge)
    }

    private fun savePersonAndItems(
        bankingBillet: BankingBillet,
        items: List<Item>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.savePerson(PersonData(bankingBillet.customer))
            itemRepository.saveItem(items)
        }
    }

    private fun trySendCharge(charge: Charge, token: String) {
        chargeRepository.makeCharge(charge, token, object : BaseCallback.ResponseCallback<ChargeResponse> {
            override fun inSucess(result: ChargeResponse) {
                callResult.value = Sucess<ChargeResponse>(result)
            }
            override fun inFailure(error: String) {
                sessionManager.deleteAuthToken()
                autenticate(charge)
            }
        })
    }

    private fun autenticate(charge: Charge) {
        autenticationRepository.autenticate(object : BaseCallback.ResponseCallback<Token> {
            override fun inSucess(result: Token) {
                sessionManager.saveAuthToken(result.accessToken)
                sendChargePosAutenticate(charge, "${result.tokenType} ${result.accessToken}")
            }
            override fun inFailure(error: String) {
                callResult.value = Failure<ChargeResponse>(error)
            }

        })
    }

    private fun sendChargePosAutenticate(charge: Charge, token: String) {
        chargeRepository.makeCharge(charge, token, object : BaseCallback.ResponseCallback<ChargeResponse> {
            override fun inSucess(result: ChargeResponse) {
                callResult.value = Sucess<ChargeResponse>(result)
            }
            override fun inFailure(error: String) {
                callResult.value = Failure<ChargeResponse>(error)
            }

        })
    }

}