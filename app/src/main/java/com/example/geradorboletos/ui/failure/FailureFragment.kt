package com.example.geradorboletos.ui.failure

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.databinding.FailureFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import javax.inject.Inject

class FailureFragment : Fragment() {

    lateinit var binding: FailureFragmentBinding

    private val controler by lazy {
        findNavController()
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<FailureViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.failureComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FailureFragmentBinding.inflate(inflater, container, false)
        binding.backToAditionalInformation = View.OnClickListener { backToAditionalInformation() }
        return binding.root
    }


    fun backToAditionalInformation(){
        FailureFragmentDirections.actionFailureFragmentToAditionalInformationFragment().run {
            controler.navigate(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}