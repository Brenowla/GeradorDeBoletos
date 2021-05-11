package com.example.geradorboletos.ui.confirmation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import javax.inject.Inject

class ConfirmationFragment : Fragment() {

    @Inject
    lateinit var viewModel: ConfirmationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.confirmation_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.confirmationComponent().create().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmationViewModel::class.java)

    }

}