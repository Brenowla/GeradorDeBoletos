package com.example.geradorboletos.ui.send_charge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import javax.inject.Inject

class SendChargeFragment : Fragment() {


    @Inject
    lateinit var viewModel: SendChargeViewModel

    private val arguments by navArgs<SendChargeFragmentArgs>()
    private val bankingBillet by lazy {
        arguments.bankingBillet
    }
    private val items by lazy {
        arguments.items
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.sendChargeComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.send_charge_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeCharge()
        viewModel.callResult.observe(viewLifecycleOwner, Observer {
            if(it.error != null){
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(context, "Tudo certo!", Toast.LENGTH_LONG).show()
            }
        })

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun makeCharge() {
        viewModel.sendCharge(bankingBillet, items.asList())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}