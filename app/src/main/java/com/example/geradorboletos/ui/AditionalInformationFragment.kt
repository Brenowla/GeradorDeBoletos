package com.example.geradorboletos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geradorboletos.R

class AditionalInformationFragment : Fragment() {

    companion object {
        fun newInstance() = AditionalInformationFragment()
    }

    private lateinit var viewModel: AditionalInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.aditional_information_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AditionalInformationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}