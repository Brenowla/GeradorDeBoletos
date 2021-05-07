package com.example.geradorboletos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geradorboletos.databinding.ActivityMainBinding
import com.example.geradorboletos.models.*
import com.example.geradorboletos.repositories.autentication.AutenticationRepository
import com.example.geradorboletos.repositories.database.ItemRepository
import com.example.geradorboletos.repositories.database.PersonRepository
import com.example.geradorboletos.repositories.retrofit.ChargeRepository
import com.example.geradorboletos.session.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var autenticationRepository: AutenticationRepository

    @Inject
    lateinit var personRepository: PersonRepository

    @Inject
    lateinit var itemRepository: ItemRepository

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var chargeRepository: ChargeRepository

    var scope = CoroutineScope(Dispatchers.IO)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        GeradorBoletosApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        val person = Person(name = "Gorbadoc Oldbbuck", cpf = "94271564656", phoneNumber = "5144916523", email = "brenowla@gmail.com", juridicalPerson = null, address = null)
        val items = arrayListOf<Item>()
        items.add(Item("Colar", 20000, 1))
        items.add(Item("Anel", 30000,2))
        val bankingBillet = BankingBillet(person,"2021-05-10")
        val payment = Payment(bankingBillet)
        val charge = Charge(items,payment)

//        autenticationRepository.autenticate(object : BaseCallback.ResponseCallback<Token> {
//            override fun inSucess(result: Token) {
////                Toast.makeText(this@MainActivity, result.accessToken, Toast.LENGTH_LONG).show()
//                sessionManager.saveAuthToken(result.accessToken)
//            }
//            override fun inFailure(error: String) {
//                Log.i("Erro", error)
//            }
//
//        })
//        chargeRepository.makeCharge(charge, object : BaseCallback.ResponseCallback<ChargeResponse> {
//            override fun inSucess(result: ChargeResponse) {
//                Toast.makeText(this@MainActivity, result.chargeId.toString(), Toast.LENGTH_LONG).show()
//            }
//
//            override fun inFailure(error: String) {
//                Log.i("Erro", error)
//            }
//        })

//        scope.launch {
//            personRepository.savePerson(PersonData(person))
//            Log.i("Pós de verdade", "onCreate: Adicionou Pessoa")
//        }
//
//        scope.launch {
//            itemRepository.saveItem(items)
//            Log.i("Pós de verdade", "onCreate: Adicionou Items")
//        }


    }



}