package com.example.geradorboletos.ui.aditional_information

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AditionalInformationFragmentBinding
import com.example.geradorboletos.models.BankingBillet
import javax.inject.Inject

class AditionalInformationFragment : Fragment() {

    lateinit var binding : AditionalInformationFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    private val arguments by navArgs<AditionalInformationFragmentArgs>()
    private val person by lazy {
        arguments.person
    }
    private val items by lazy {
        arguments.items
    }

    @Inject
    lateinit var viewModel: AditionalInformationViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.aditionalItensComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AditionalInformationFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.toSendCharge = View.OnClickListener { toSendCharge() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bankingBilletData.update(BankingBillet(person, ""))
        viewModel.updateTotal(items.toList())

        activity?.title = getString(R.string.emissao_boletos)
    }

    fun toSendCharge() {
        val bankingBillet = viewModel.bankingBilletData.toBankingBillet()
        AditionalInformationFragmentDirections.actionAditionalInformationFragmentToSendChargeFragment(bankingBillet,items).run {
            controler.navigate(this)
        }
    }

}