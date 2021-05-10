package com.example.geradorboletos.ui.form_person

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.FormPersonFragmentBinding
import javax.inject.Inject

class FormPersonFragment : Fragment() {

    lateinit var binding: FormPersonFragmentBinding

    @Inject
    lateinit var viewModel: FormPersonViewModel

    private val controler by lazy {
        findNavController()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.formPersonComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FormPersonFragmentBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.personBinding.name.observe(viewLifecycleOwner, Observer {
            if(it == "Breno"){
                Toast.makeText(activity, "Deu certo!", Toast.LENGTH_LONG).show()
            }
        })
        binding.toAddItems = View.OnClickListener {
            toAddItems()
        }

        activity?.title = getString(R.string.emissao_boletos)
    }

    private fun toAddItems() {
        FormPersonFragmentDirections.actionFormPersonFragmentToAddItemsFragment(viewModel.getPerson())
            .run {
                controler.navigate(this)
            }
    }
}