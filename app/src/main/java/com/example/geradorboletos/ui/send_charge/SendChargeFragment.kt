package com.example.geradorboletos.ui.send_charge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.SendChargeFragmentBinding
import com.example.geradorboletos.models.BankingBillet
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class SendChargeFragment : Fragment() {

    lateinit var binding: SendChargeFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<SendChargeViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.sendChargeComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SendChargeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animate()
        makeCharge()

        viewModel.callResult.observe(viewLifecycleOwner, Observer {
            if(it.error != null){
                SendChargeFragmentDirections.actionSendChargeFragmentToFailureFragment().run {
                    controler.navigate(this)
                }
            }else {
                mainViewModel.chargeResponse = it.data
                viewModel.savePersonAndItems(mainViewModel.costumer!!,mainViewModel.items)
                SendChargeFragmentDirections.actionSendChargeFragmentToConfirmationFragment().run {
                    controler.navigate(this)
                }
            }
        })

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun animate() {
        val rotate = AnimationUtils.loadAnimation(context, R.anim.rotate)
        rotate.fillAfter = true
        binding.sendChargeRotatingGroup.startAnimation(rotate)
    }

    private fun makeCharge() {
        val person = mainViewModel.costumer
        if(person != null && mainViewModel.expireAt != null){
            val bankingBillet = BankingBillet(person, mainViewModel.expireAt!!)
            viewModel.sendCharge(bankingBillet, mainViewModel.items)
        }
    }

}
