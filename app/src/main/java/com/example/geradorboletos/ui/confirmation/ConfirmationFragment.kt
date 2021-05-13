package com.example.geradorboletos.ui.confirmation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.databinding.ConfirmationFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class ConfirmationFragment : Fragment() {

    lateinit var binding: ConfirmationFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<ConfirmationViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfirmationFragmentBinding.inflate(inflater, container, false)
        binding.chargeresponse = mainViewModel.chargeResponse
        binding.person = mainViewModel.costumer
        binding.backToFormPerson = View.OnClickListener { backToFormPerson() }
        return binding.root
    }

    fun backToFormPerson(){
        mainViewModel.resetData()
        ConfirmationFragmentDirections.actionConfirmationFragmentToFormPersonFragment().run {
            controler.navigate(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.confirmationComponent().create().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}