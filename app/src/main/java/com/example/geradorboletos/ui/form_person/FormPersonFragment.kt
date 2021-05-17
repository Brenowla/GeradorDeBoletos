package com.example.geradorboletos.ui.form_person

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
import com.example.geradorboletos.databinding.FormPersonFragmentBinding
import com.example.geradorboletos.ui.MainActivity
import com.example.geradorboletos.ui.MainViewModel
import javax.inject.Inject

class FormPersonFragment : Fragment() {

    lateinit var binding: FormPersonFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FormPersonViewModel> { viewModelFactory }
    private val mainViewModel by viewModels<MainViewModel>({activity as MainActivity}) { viewModelFactory }

    private val controler by lazy {
        findNavController()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mainComponent.formPersonComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FormPersonFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.costumer?.let {
            viewModel.updatePerson(it)
        }
        binding.toAddItems = View.OnClickListener {
            toAddItems()
        }
        binding.toListPerson = View.OnClickListener {
            toListPerson()
        }

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun toAddItems() {
        mainViewModel.costumer = viewModel.getPerson()
        FormPersonFragmentDirections.actionFormPersonFragmentToAddItemsFragment()
            .run {
                controler.navigate(this)
            }
    }

    private fun toListPerson() {
        mainViewModel.costumer = viewModel.getPerson()
        FormPersonFragmentDirections.actionFormPersonFragmentToListPersonFragment().run {
            controler.navigate(this)
        }
    }

}