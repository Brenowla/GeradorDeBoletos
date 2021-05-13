package com.example.geradorboletos.ui.aditional_information

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AditionalInformationFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class AditionalInformationFragment : Fragment() {

    lateinit var binding : AditionalInformationFragmentBinding

    private val controler by lazy {
        findNavController()
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AditionalInformationViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.aditionalItensComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AditionalInformationFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.toSendCharge = View.OnClickListener { toSendCharge() }
        binding.backToAddItens = View.OnClickListener { backToAddItens() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateTotal(mainViewModel.items)

        activity?.title = getString(R.string.emissao_boletos)
    }

    fun toSendCharge() {
        mainViewModel.expireAt = viewModel.expireAt.value
        AditionalInformationFragmentDirections.actionAditionalInformationFragmentToSendChargeFragment().run {
            controler.navigate(this)
        }
    }

    fun backToAddItens(){
        mainViewModel.expireAt = viewModel.expireAt.value
        AditionalInformationFragmentDirections.actionAditionalInformationFragmentToAddItemsFragment().run {
            controler.navigate(this)
        }
    }

}