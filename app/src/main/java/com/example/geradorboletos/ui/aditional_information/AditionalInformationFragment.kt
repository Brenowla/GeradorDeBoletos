package com.example.geradorboletos.ui.aditional_information

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.databinding.AditionalInformationFragmentBinding
import javax.inject.Inject

class AditionalInformationFragment : Fragment() {

    lateinit var binding : AditionalInformationFragmentBinding


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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}