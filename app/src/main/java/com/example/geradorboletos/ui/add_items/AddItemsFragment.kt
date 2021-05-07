package com.example.geradorboletos.ui.add_items

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geradorboletos.GeradorBoletosApp
import com.example.geradorboletos.R
import com.example.geradorboletos.databinding.AddItemsFragmentBinding
import com.example.geradorboletos.databinding.FormItemBinding
import javax.inject.Inject

class AddItemsFragment : Fragment() {

    lateinit var binding : AddItemsFragmentBinding

    private val controler by lazy {
        findNavController()
    }
    private val arguments by navArgs<AddItemsFragmentArgs>()
    private val person by lazy {
        arguments.person
    }

    @Inject
    lateinit var viewModel: AddItemsViewModel

    val adapter by lazy {
        ListItemsAdapter(requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GeradorBoletosApp.appComponent.addItemsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddItemsFragmentBinding.inflate(inflater,container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addItemListener = View.OnClickListener { dialogFormItens() }
        binding.toAditionalItems = View.OnClickListener { toAditionalInformation() }
        binding.addItemsLista.adapter = adapter

        viewModel.listItemsData.observe(viewLifecycleOwner, Observer {
            adapter.changeList(it)
        })


    }

    private fun toAditionalInformation() {
        AddItemsFragmentDirections.actionAddItemsFragmentToAditionalInformationFragment(person,
            viewModel.getLista().toTypedArray()
        ).run {
            controler.navigate(this)
        }

    }

    private fun dialogFormItens () {
        val dialogBinding : FormItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.form_item, null, false)
        dialogBinding.viewModel = viewModel
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.add_item))
        builder.setView(dialogBinding.root)
        builder.setPositiveButton("Adicionar") { dialogInterface: DialogInterface, i: Int ->
            viewModel.addItem()
        }
        builder.setNegativeButton("Cancelar"){ dialogInterface: DialogInterface, i: Int ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}